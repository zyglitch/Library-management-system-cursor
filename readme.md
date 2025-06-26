# 图书管理系统

## 项目概述
本项目是一个完整的图书管理系统，采用前后端分离架构，前端基于 Vue3 + Element Plus，后端基于 Java Spring Boot + MyBatis-Plus，数据库为 MySQL。系统支持图书借阅、归还、续借、欠费管理等功能，服务于学生、教职工和管理员三类用户。

## 技术栈
- 前端：Vue3, Element Plus, Axios, Pinia, Vue Router, Vite
- 后端：Java, Spring Boot, MyBatis-Plus, Spring Security
- 数据库：MySQL

## 管理员初始账号（可用于登录测试）
| 用户名   | 密码      | 角色         |
|----------|-----------|--------------|
| admin1   | 123456    | 超级管理员   |
| admin2   | 123456    | 普通管理员   |
| manager  | 123456    | 普通管理员   |

> 请在数据库 `Administrators` 表中插入以上账号，或在初始化时手动添加。

## 数据库初始化

### 1. 创建数据库
```sql
CREATE DATABASE library_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 创建管理员表
```sql
CREATE TABLE Administrators (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(50),
    role VARCHAR(20),
    phone VARCHAR(20),
    email VARCHAR(100),
    register_time DATETIME
);
```

### 3. 插入初始管理员账号
```sql
INSERT INTO Administrators (username, password, name, role, phone, email, register_time)
VALUES
('admin1', '123456', '超级管理员', '超级管理员', '12345678901', 'admin1@example.com', NOW()),
('admin2', '123456', '普通管理员', '普通管理员', '12345678902', 'admin2@example.com', NOW()),
('manager', '123456', '普通管理员', '普通管理员', '12345678903', 'manager@example.com', NOW());
```

### 4. 创建欠费记录表
```sql
CREATE TABLE Fees (
    fee_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(50) NOT NULL,
    user_type VARCHAR(20) NOT NULL,
    record_id INT,
    fee_type VARCHAR(50) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    due_date DATE NOT NULL,
    pay_date DATE,
    status VARCHAR(20) DEFAULT '未缴'
);
```

### 5. 插入测试欠费记录
```sql
INSERT INTO Fees (user_id, user_type, fee_type, amount, due_date, status)
VALUES
('admin1', 'admin', '逾期罚款', 50.00, '2024-01-15', '未缴'),
('admin2', 'admin', '损坏赔偿', 100.00, '2024-01-20', '未缴');
```

## 项目结构

### 后端目录结构
```
library-management-system/
├─ backend/
│   ├─ src/
│   │   ├─ main/
│   │   │   ├─ java/com/example/library/
│   │   │   │   ├─ controller/    # 控制器层
│   │   │   │   ├─ service/       # 业务接口
│   │   │   │   ├─ service/impl/  # 业务实现
│   │   │   │   ├─ mapper/        # MyBatis-Plus Mapper
│   │   │   │   ├─ model/         # 实体类
│   │   │   │   ├─ config/        # 配置类
│   │   │   │   └─ LibraryApplication.java # 启动类
│   │   └─ resources/
│   │       ├─ application.yml
│   │       └─ mapper/            # MyBatis XML（如有自定义SQL）
│   └─ pom.xml
```

### 前端目录结构
```
frontend/
├─ public/
├─ src/
│  ├─ api/                # 封装接口请求
│  ├─ components/         # 公共组件
│  ├─ views/
│  │   ├─ Login.vue       # 登录页
│  │   └─ BookList.vue    # 图书管理页
│  ├─ router/
│  │   └─ index.js        # 路由配置
│  ├─ store/
│  │   └─ index.js        # 状态管理
│  ├─ App.vue
│  └─ main.js
├─ package.json
└─ vite.config.js
```

## 启动与运行

### 1. 启动后端
1. 进入 backend 目录：
   ```bash
   cd backend
   ```
2. 安装依赖并编译：
   ```bash
   mvn clean install
   ```
3. 启动 Spring Boot 应用：
   ```bash
   mvn spring-boot:run
   ```
4. 后端默认端口为 8080。

### 2. 启动前端
1. 进入 frontend 目录：
   ```bash
   cd ../frontend
   ```
2. 安装依赖：
   ```bash
   npm install
   ```
3. 启动开发服务器：
   ```bash
   npm run dev
   ```
4. 打开浏览器访问 [http://localhost:3000/](http://localhost:3000/)

### 3. 登录系统
- 使用上表中的管理员账号登录。
- 登录成功后可进入图书管理页面。

## 主要功能
- 多角色用户系统（学生、教职工、管理员）
- 图书检索与借阅、归还、续借
- 借阅记录管理
- 欠费管理与支付
- 管理员统计与报表

## API文档
后端接口为 RESTful 风格，具体接口见 controller 代码。

## 开发者
- [9月13]

## 许可证
MIT License
