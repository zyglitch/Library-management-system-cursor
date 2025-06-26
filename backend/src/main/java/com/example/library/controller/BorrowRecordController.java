package com.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.model.BorrowRecord;
import com.example.library.service.BorrowRecordService;
import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;
import java.util.Calendar;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/borrow-records")
public class BorrowRecordController {
    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public Map<String, Object> list(@RequestParam(required = false) String userId,
                                  @RequestParam(required = false) String userType) {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<BorrowRecord> queryWrapper = new QueryWrapper<>();
        
        // 如果是管理员，显示所有记录
        if (userType != null && userType.equals("admin")) {
            // 不添加任何条件，返回所有记录
        } else if (userId != null && userType != null) {
            // 非管理员只能查看自己的记录
            queryWrapper.eq("user_id", userId)
                      .eq("user_type", userType);
        }
        
        List<BorrowRecord> records = borrowRecordService.list(queryWrapper);
        
        // 获取所有相关的图书信息
        Set<String> bookIds = records.stream()
                .map(BorrowRecord::getBookId)
                .collect(Collectors.toSet());
        
        final Map<String, Book> bookMap = new HashMap<>();
        if (!bookIds.isEmpty()) {
            QueryWrapper<Book> bookQuery = new QueryWrapper<>();
            bookQuery.in("book_id", bookIds);
            List<Book> books = bookService.list(bookQuery);
            books.forEach(book -> bookMap.put(book.getBookId(), book));
        }
        
        // 将图书信息添加到借阅记录中
        records.forEach(record -> {
            record.setBook(bookMap.get(record.getBookId()));
        });
        
        result.put("code", 0);
        result.put("msg", "获取借阅记录成功");
        result.put("data", records);
        System.out.println("获取借阅记录成功，共" + records.size() + "条");
        return result;
    }

    @GetMapping("/page")
    public Page<BorrowRecord> page(@RequestParam(defaultValue = "1") int current,
                                  @RequestParam(defaultValue = "10") int size) {
        return borrowRecordService.page(new Page<>(current, size));
    }

    @GetMapping("/{id}")
    public BorrowRecord getById(@PathVariable Integer id) {
        return borrowRecordService.getById(id);
    }

    @PostMapping
    public boolean add(@RequestBody BorrowRecord borrowRecord) {
        return borrowRecordService.save(borrowRecord);
    }

    @PutMapping
    public boolean update(@RequestBody BorrowRecord borrowRecord) {
        return borrowRecordService.updateById(borrowRecord);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return borrowRecordService.removeById(id);
    }

    @PutMapping("/return/{id}")
    public Map<String, Object> returnBook(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            BorrowRecord record = borrowRecordService.getById(id);
            if (record == null) {
                result.put("code", 1);
                result.put("msg", "借阅记录不存在");
                return result;
            }
            if ("已归还".equals(record.getStatus())) {
                result.put("code", 1);
                result.put("msg", "该书籍已经归还");
                return result;
            }
            record.setStatus("已归还");
            record.setReturnDate(new Date());
            borrowRecordService.updateById(record);
            result.put("code", 0);
            result.put("msg", "归还成功");
        } catch (Exception e) {
            result.put("code", 1);
            result.put("msg", "归还失败: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @PostMapping("/borrow")
    public Map<String, Object> borrowBook(@RequestBody Map<String, Object> borrowDTO) {
        Map<String, Object> result = new HashMap<>();
        try {
            String userId = (String) borrowDTO.get("userId");
            String userType = (String) borrowDTO.get("userType");
            String bookId = (String) borrowDTO.get("bookId");
            
            if (userId == null || userType == null || bookId == null) {
                result.put("code", 1);
                result.put("msg", "参数不完整");
                return result;
            }
            
            // 创建借阅记录
            BorrowRecord record = new BorrowRecord();
            record.setUserId(userId);
            record.setUserType(userType);
            record.setBookId(bookId);
            record.setBorrowDate(new Date());
            
            // 根据用户类型设置不同的借阅期限
            Calendar cal = Calendar.getInstance();
            if ("student".equals(userType)) {
                cal.add(Calendar.DAY_OF_MONTH, 30); // 学生30天
            } else if ("faculty".equals(userType)) {
                cal.add(Calendar.DAY_OF_MONTH, 60); // 教职工60天
            } else {
                cal.add(Calendar.DAY_OF_MONTH, 30); // 默认30天
            }
            record.setDueDate(cal.getTime());
            record.setStatus("借阅中");
            record.setOperatorId(1); // 默认管理员ID
            
            borrowRecordService.save(record);
            result.put("code", 0);
            result.put("msg", "借阅成功");
        } catch (Exception e) {
            result.put("code", 1);
            result.put("msg", "借阅失败: " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
} 