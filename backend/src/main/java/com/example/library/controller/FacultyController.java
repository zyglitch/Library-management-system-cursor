package com.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.model.Faculty;
import com.example.library.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public List<Faculty> list() {
        return facultyService.list();
    }

    @GetMapping("/page")
    public Page<Faculty> page(@RequestParam(defaultValue = "1") int current,
                             @RequestParam(defaultValue = "10") int size) {
        return facultyService.page(new Page<>(current, size));
    }

    @GetMapping("/{id}")
    public Faculty getById(@PathVariable String id) {
        return facultyService.getById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Faculty faculty) {
        return facultyService.save(faculty);
    }

    @PutMapping
    public boolean update(@RequestBody Faculty faculty) {
        return facultyService.updateById(faculty);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return facultyService.removeById(id);
    }
} 