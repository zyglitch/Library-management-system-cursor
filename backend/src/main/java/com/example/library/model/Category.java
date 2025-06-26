package com.example.library.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("Categories")
public class Category {
    @TableId(type = IdType.AUTO)
    private Integer categoryId; // 分类唯一标识
    private String categoryName; // 分类名称
    private Integer parentId; // 父分类ID
    private String description; // 分类说明
} 