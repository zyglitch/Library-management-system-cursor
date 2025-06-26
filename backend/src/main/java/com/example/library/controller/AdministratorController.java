package com.example.library.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.library.model.Administrator;
import com.example.library.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @GetMapping
    public List<Administrator> list() {
        return administratorService.list();
    }

    @GetMapping("/page")
    public Page<Administrator> page(@RequestParam(defaultValue = "1") int current,
                                   @RequestParam(defaultValue = "10") int size) {
        return administratorService.page(new Page<>(current, size));
    }

    @GetMapping("/{id}")
    public Administrator getById(@PathVariable Integer id) {
        return administratorService.getById(id);
    }

    @PostMapping
    public boolean add(@RequestBody Administrator administrator) {
        return administratorService.save(administrator);
    }

    @PutMapping
    public boolean update(@RequestBody Administrator administrator) {
        return administratorService.updateById(administrator);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return administratorService.removeById(id);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> loginDTO) {
        String username = loginDTO.get("username");
        String password = loginDTO.get("password");
        Administrator admin = administratorService.lambdaQuery()
            .eq(Administrator::getUsername, username)
            .one();
        Map<String, Object> result = new HashMap<>();
        if (admin == null || !admin.getPassword().equals(password)) {
            result.put("code", 1);
            result.put("msg", "用户名或密码错误");
            return result;
        }
        // 根据用户名设置不同的用户类型
        String userType = "admin";
        if ("admin1".equals(username)) {
            userType = "student";
        } else if ("admin2".equals(username)) {
            userType = "faculty";
        }
        Map<String, Object> userData = new HashMap<>();
        userData.put("user", admin);
        userData.put("token", "mock-token");
        userData.put("userType", userType);
        result.put("code", 0);
        result.put("msg", "登录成功");
        result.put("data", userData);
        return result;
    }
} 