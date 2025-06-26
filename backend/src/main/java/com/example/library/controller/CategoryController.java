package com.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.model.Category;
import com.example.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> list() {
        return categoryService.list();
    }

    @GetMapping("/page")
    public Page<Category> page(@RequestParam(defaultValue = "1") int current,
                              @RequestParam(defaultValue = "10") int size) {
        return categoryService.page(new Page<>(current, size));
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Integer id) {
        return categoryService.getById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping
    public boolean update(@RequestBody Category category) {
        return categoryService.updateById(category);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return categoryService.removeById(id);
    }
} 