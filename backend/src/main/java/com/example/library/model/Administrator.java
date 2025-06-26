package com.example.library.model;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.util.Date;

@Data
@TableName("Administrators")
public class Administrator {
    @TableId(type = IdType.AUTO)
    private Integer adminId; // 管理员唯一标识
    private String username; // 登录用户名
    private String password; // 登录密码（加密）
    private String name; // 姓名
    private String role; // 权限角色
    private String phone; // 联系方式
    private String email; // 电子邮箱
    private Date registerTime; // 注册时间

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
} 