# 图书管理系统

## 项目概述
本项目是一个完整的图书管理系统，采用前后端分离架构，前端基于 Vue3 + Element Plus，后端基于 Java Spring Boot + MyBatis-Plus，数据库为 MySQL。系统支持图书借阅、归还、续借、欠费管理等功能，服务于学生、教职工和管理员三类用户。

## 技术栈
### 前端
- 核心框架：Vue 3
- UI组件库：Element Plus
- 状态管理：Pinia
- 路由管理：Vue Router
- 构建工具：Vite
- HTTP客户端：Axios

### 后端
- 核心框架：Spring Boot
- ORM框架：MyBatis-Plus
- 安全框架：Spring Security
- 数据库：MySQL
- 接口文档：Swagger/OpenAPI

## 用户角色说明
系统支持三种类型的用户：

1. 学生用户：
   - 图书查询和借阅
   - 个人借阅记录查看
   - 欠费查询和支付
   - 图书续借
   - 图书归还

2. 教职工用户：
   - 具有更长的借阅期限
   - 图书查询和借阅
   - 个人借阅记录查看
   - 欠费查询和支付
   - 图书续借和归还

3. 管理员用户：
   - 用户管理
   - 图书信息管理
   - 借阅记录管理
   - 欠费记录管理
   - 系统报表生成

## 项目结构

### 后端结构
```
backend/
├─ src/main/java/com/example/library/
│   ├─ config/           # 系统配置
│   │   ├─ CorsConfig.java
│   │   └─ SecurityConfig.java
│   ├─ controller/       # 接口控制器
│   ├─ service/         # 业务逻辑
│   │   └─ impl/       # 业务实现
│   ├─ mapper/         # 数据访问
│   ├─ model/          # 数据模型
│   └─ LibraryApplication.java
├─ src/main/resources/
│   └─ application.yml  # 配置文件
└─ pom.xml             # 依赖管理
```

### 前端结构
```
frontend/
├─ src/
│  ├─ api/            # API接口
│  │   ├─ auth.js    # 认证接口
│  │   ├─ book.js    # 图书接口
│  │   ├─ borrow.js  # 借阅接口
│  │   └─ fee.js     # 费用接口
│  ├─ views/         # 页面组件
│  │   ├─ AdminHome.vue
│  │   ├─ BookList.vue
│  │   ├─ BorrowRecords.vue
│  │   ├─ FacultyHome.vue
│  │   ├─ FeeManage.vue
│  │   ├─ Login.vue
│  │   ├─ MyBorrow.vue
│  │   ├─ MyFee.vue
│  │   └─ StudentHome.vue
│  ├─ router/        # 路由配置
│  ├─ store/         # 状态管理
│  ├─ App.vue
│  └─ main.js
├─ index.html
├─ package.json
└─ vite.config.js
```

## 核心功能模块

### 1. 用户管理
- 多角色登录系统
- 基于JWT的身份认证
- 细粒度的权限控制
- 用户信息管理

### 2. 图书管理
- 图书信息的CRUD操作
- 图书分类管理
- 出版社信息管理
- 库存状态追踪

### 3. 借阅管理
- 图书借阅处理
- 归还处理
- 续借功能
- 借阅历史查询
- 借阅状态追踪

### 4. 费用管理
- 逾期罚款计算
- 损坏赔偿处理
- 费用记录查询
- 在线支付功能

### 5. 统计分析
- 借阅数据统计
- 费用统计分析
- 图书流通分析
- 用户行为分析

## 环境要求
- JDK 17+
- Node.js 16+
- MySQL 8.0+

## 快速开始

### 1. 数据库配置
```sql
CREATE DATABASE library_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
mysql -u root -p library_db < database_init.sql
```

### 2. 后端服务启动
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

### 3. 前端服务启动
```bash
cd frontend
npm install
npm run dev
```

## 测试账号
| 用户名 | 密码   | 角色   |
|--------|--------|--------|
| admin1 | 123456 | 学生   |
| admin2 | 123456 | 教职工 |
| manager| 123456 | 管理员 |

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