package com.example.library.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("borrowrecords")
public class BorrowRecord {
    @TableId("record_id")
    private Integer recordId; // 借阅记录唯一标识
    
    @TableField("user_id")
    private String userId; // 借阅人唯一标识
    
    @TableField("user_type")
    private String userType; // 借阅人类型
    
    @TableField("book_id")
    private String bookId; // 图书ID
    
    @TableField("borrow_date")
    private Date borrowDate; // 借阅日期
    
    @TableField("due_date")
    private Date dueDate; // 应还日期
    
    @TableField("return_date")
    private Date returnDate; // 实际归还日期
    
    @TableField("status")
    private String status; // 借阅状态
    
    @TableField("fine_amount")
    private BigDecimal fineAmount; // 罚金金额
    
    @TableField("operator_id")
    private Integer operatorId; // 操作员ID

    @TableField(exist = false)
    private Book book; // 关联的图书信息
} 