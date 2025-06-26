package com.example.library.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("Fees")
public class Fee {
    @TableId(type = IdType.AUTO)
    private Integer feeId; // 欠费记录唯一标识
    private String userId; // 欠费人唯一标识
    private String userType; // 欠费人类型
    private Integer recordId; // 借阅记录ID
    private String feeType; // 欠费类型
    private Double amount; // 欠费金额
    private Date dueDate; // 应缴费日期
    private Date payDate; // 实际缴费日期
    private String status; // 欠费状态
} 