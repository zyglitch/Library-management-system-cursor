package com.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.model.Publisher;
import com.example.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public List<Publisher> list() {
        return publisherService.list();
    }

    @GetMapping("/page")
    public Page<Publisher> page(@RequestParam(defaultValue = "1") int current,
                               @RequestParam(defaultValue = "10") int size) {
        return publisherService.page(new Page<>(current, size));
    }

    @GetMapping("/{id}")
    public Publisher getById(@PathVariable Integer id) {
        return publisherService.getById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Publisher publisher) {
        return publisherService.save(publisher);
    }

    @PutMapping
    public boolean update(@RequestBody Publisher publisher) {
        return publisherService.updateById(publisher);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return publisherService.removeById(id);
    }
} 