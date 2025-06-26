package com.example.library.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("Books")
public class Book {
    @TableId(type = IdType.INPUT)
    private String bookId; // 图书唯一标识
    private String title; // 书名
    private String author; // 作者
    private Integer categoryId; // 分类ID
    private Integer publisherId; // 出版社ID
    private Date publishDate; // 出版日期
    private String isbn; // ISBN编号
    private Integer totalCount; // 总库存量
    private Integer availableCount; // 可借阅数量
    private String status; // 图书状态（在架、借出、损坏、丢失）
    private String shelfLocation; // 书架位置
    private String description; // 图书简介

    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public Integer getPublisherId() { return publisherId; }
    public void setPublisherId(Integer publisherId) { this.publisherId = publisherId; }

    public Date getPublishDate() { return publishDate; }
    public void setPublishDate(Date publishDate) { this.publishDate = publishDate; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getTotalCount() { return totalCount; }
    public void setTotalCount(Integer totalCount) { this.totalCount = totalCount; }

    public Integer getAvailableCount() { return availableCount; }
    public void setAvailableCount(Integer availableCount) { this.availableCount = availableCount; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getShelfLocation() { return shelfLocation; }
    public void setShelfLocation(String shelfLocation) { this.shelfLocation = shelfLocation; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
} 