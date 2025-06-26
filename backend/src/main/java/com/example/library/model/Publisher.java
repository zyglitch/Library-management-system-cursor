package com.example.library.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("Publishers")
public class Publisher {
    @TableId(type = IdType.AUTO)
    private Integer publisherId; // 出版社唯一标识
    private String publisherName; // 出版社名称
    private String address; // 地址
    private String phone; // 联系电话
    private String email; // 电子邮箱
    private String website; // 官方网站
} 