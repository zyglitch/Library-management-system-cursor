# 图书管理系统

## 项目概述
本项目是一个完整的图书管理系统，采用前后端分离架构，前端基于 Vue3 + Element Plus，后端基于 Java Spring Boot + MyBatis-Plus，数据库为 MySQL。系统支持图书借阅、归还、续借、欠费管理等功能，服务于学生、教职工和管理员三类用户。

## 技术栈
- 前端：Vue3, Element Plus, Axios, Pinia, Vue Router, Vite
- 后端：Java, Spring Boot, MyBatis-Plus, Spring Security
- 数据库：MySQL

## 用户角色说明
系统支持三种类型的用户：

1. 学生用户（以 admin1 为例）：
   - 可以查看和借阅图书
   - 可以查看个人借阅记录
   - 可以查看和支付欠费
   - 可以续借图书
   - 可以归还图书

2. 教职工用户（以 admin2 为例）：
   - 具有比学生更长的借阅期限
   - 可以查看个人借阅记录
   - 可以查看和支付欠费
   - 可以续借图书
   - 可以归还图书

3. 管理员用户（以 manager 为例）：
   - 可以查看所有用户的借阅记录
   - 可以管理图书信息
   - 可以处理借阅和归还请求
   - 可以管理欠费记录
   - 可以生成统计报表

## 初始账号信息
| 用户名 | 密码   | 角色类型 | 说明                           |
|--------|--------|----------|--------------------------------|
| admin1 | 123456 | 学生     | 用于测试学生用户功能           |
| admin2 | 123456 | 教职工   | 用于测试教职工用户功能         |
| manager| 123456 | 管理员   | 用于测试管理员功能，可查看所有记录 |

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

## 数据库表结构
系统包含以下主要数据表：
1. `administrators` - 管理员信息表
2. `books` - 图书信息表
3. `borrowrecords` - 借阅记录表
4. `categories` - 图书分类表
5. `faculty` - 教职工信息表
6. `fees` - 欠费记录表
7. `publishers` - 出版社信息表
8. `students` - 学生信息表

详细的表结构见 `database_init.sql` 文件。

## 启动说明

### 1. 数据库初始化
1. 创建数据库：
```sql
CREATE DATABASE library_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```
2. 执行初始化脚本：
```bash
mysql -u root -p library_db < database_init.sql
```

### 2. 启动后端服务
```bash
cd backend
mvn clean install
mvn spring-boot:run
```
后端服务默认运行在 8080 端口。

### 3. 启动前端服务
```bash
cd frontend
npm install
npm run dev
```
前端服务默认运行在 3000 端口。

## 主要功能模块

### 1. 用户认证
- 多角色登录（学生、教职工、管理员）
- 基于 JWT 的身份验证
- 权限控制

### 2. 图书管理
- 图书信息维护
- 库存管理
- 分类管理
- 出版社管理

### 3. 借阅管理
- 图书借阅
- 图书归还
- 续借功能
- 借阅历史查询

### 4. 欠费管理
- 逾期罚款
- 损坏赔偿
- 欠费记录查询
- 在线缴费

### 5. 统计报表
- 借阅统计
- 欠费统计
- 图书库存统计

## 开发者
- [9月13]

## 许可证
MIT License



USE library_db;

-- 查看管理员表
SELECT * FROM administrators;

-- 查看图书表
SELECT * FROM books;

-- 查看借阅记录表
SELECT * FROM borrowrecords;

-- 查看图书分类表
SELECT * FROM categories;

-- 查看教职工表
SELECT * FROM faculty;

-- 查看费用表
SELECT * FROM fees;

-- 查看出版社表
SELECT * FROM publishers;

-- 查看学生表
SELECT * FROM students;