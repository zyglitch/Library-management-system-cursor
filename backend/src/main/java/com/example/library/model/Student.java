package com.example.library.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("Students")
public class Student {
    @TableId(type = IdType.INPUT)
    private String studentId; // 学生唯一标识
    private String name; // 姓名
    private String clazz; // 班级
    private String department; // 学院/系别
    private String phone; // 联系方式
    private String email; // 电子邮箱
    private Integer borrowCount; // 当前借阅数量
    private Boolean isOverdue; // 是否有逾期未还
    private Double debtAmount; // 累计欠费金额
    private Date registerTime; // 注册时间
} 