package com.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.model.Student;
import com.example.library.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> list() {
        return studentService.list();
    }

    @GetMapping("/page")
    public Page<Student> page(@RequestParam(defaultValue = "1") int current,
                             @RequestParam(defaultValue = "10") int size) {
        return studentService.page(new Page<>(current, size));
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable String id) {
        return studentService.getById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping
    public boolean update(@RequestBody Student student) {
        return studentService.updateById(student);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable String id) {
        return studentService.removeById(id);
    }
} 