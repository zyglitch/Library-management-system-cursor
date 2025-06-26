package com.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.model.Fee;
import com.example.library.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;

@RestController
@RequestMapping("/fees")
public class FeeController {
    @Autowired
    private FeeService feeService;

    private String mapUserType(String userType) {
        if (userType == null) return null;
        switch (userType.toLowerCase()) {
            case "student":
                return "学生";
            case "faculty":
                return "教职工";
            case "admin":
                return "admin";
            default:
                return userType;
        }
    }

    @GetMapping
    public Map<String, Object> list(@RequestParam(required = false) String userId,
                                  @RequestParam(required = false) String userType) {
        Map<String, Object> result = new HashMap<>();
        try {
            QueryWrapper<Fee> queryWrapper = new QueryWrapper<>();
            
            // 如果提供了用户ID和类型，只查询该用户的记录
            if (userId != null && userType != null) {
                String mappedUserType = mapUserType(userType);
                queryWrapper.eq("user_id", userId)
                          .eq("user_type", mappedUserType);
            }
            
            List<Fee> fees = feeService.list(queryWrapper);
            result.put("success", true);
            result.put("records", fees);
            result.put("total", fees.size());
            System.out.println("获取欠费记录成功，共" + fees.size() + "条");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "获取欠费记录失败：" + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/page")
    public Page<Fee> page(@RequestParam(defaultValue = "1") int current,
                         @RequestParam(defaultValue = "10") int size) {
        return feeService.page(new Page<>(current, size));
    }

    @GetMapping("/{id}")
    public Fee getById(@PathVariable Integer id) {
        return feeService.getById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Fee fee) {
        return feeService.save(fee);
    }

    @PutMapping
    public boolean update(@RequestBody Fee fee) {
        return feeService.updateById(fee);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return feeService.removeById(id);
    }

    @PostMapping("/{feeId}/pay")
    public Map<String, Object> payFee(@PathVariable Integer feeId) {
        Map<String, Object> result = new HashMap<>();
        try {
            Fee fee = feeService.getById(feeId);
            if (fee == null) {
                result.put("success", false);
                result.put("message", "未找到该欠费记录");
                return result;
            }
            
            if ("已缴".equals(fee.getStatus())) {
                result.put("success", false);
                result.put("message", "该费用已缴纳");
                return result;
            }
            
            fee.setStatus("已缴");
            fee.setPayDate(new Date());
            feeService.updateById(fee);
            
            result.put("success", true);
            result.put("message", "缴费成功");
            return result;
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "缴费失败：" + e.getMessage());
            return result;
        }
    }
} 