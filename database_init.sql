-- 创建数据库
CREATE DATABASE IF NOT EXISTS library_db DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE library_db;

-- 创建管理员表
CREATE TABLE IF NOT EXISTS Administrators (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(50),
    role VARCHAR(20),
    phone VARCHAR(20),
    email VARCHAR(100),
    register_time DATETIME
);

-- 创建图书分类表
CREATE TABLE IF NOT EXISTS Categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL,
    parent_id INT,
    description VARCHAR(255)
);

-- 创建出版社表
CREATE TABLE IF NOT EXISTS Publishers (
    publisher_id INT PRIMARY KEY AUTO_INCREMENT,
    publisher_name VARCHAR(100) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(20),
    email VARCHAR(100),
    website VARCHAR(255)
);

-- 创建图书表
CREATE TABLE IF NOT EXISTS Books (
    book_id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100),
    category_id INT,
    publisher_id INT,
    publish_date DATE,
    isbn VARCHAR(20),
    total_count INT DEFAULT 0,
    available_count INT DEFAULT 0,
    status VARCHAR(20) DEFAULT '在架',
    shelf_location VARCHAR(50),
    description TEXT,
    FOREIGN KEY (category_id) REFERENCES Categories(category_id),
    FOREIGN KEY (publisher_id) REFERENCES Publishers(publisher_id)
);

-- 创建学生表
CREATE TABLE IF NOT EXISTS Students (
    student_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    clazz VARCHAR(50),
    department VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(100),
    borrow_count INT DEFAULT 0,
    is_overdue BOOLEAN DEFAULT FALSE,
    debt_amount DECIMAL(10,2) DEFAULT 0.00,
    register_time DATETIME
);

-- 创建教职工表
CREATE TABLE IF NOT EXISTS Faculty (
    faculty_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(100),
    title VARCHAR(50),
    phone VARCHAR(20),
    email VARCHAR(100),
    borrow_count INT DEFAULT 0,
    is_overdue BOOLEAN DEFAULT FALSE,
    debt_amount DECIMAL(10,2) DEFAULT 0.00,
    register_time DATETIME
);

-- 创建借阅记录表
CREATE TABLE IF NOT EXISTS BorrowRecords (
    record_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(50) NOT NULL,
    user_type VARCHAR(20) NOT NULL,
    book_id VARCHAR(50) NOT NULL,
    borrow_date DATETIME NOT NULL,
    due_date DATETIME NOT NULL,
    return_date DATETIME,
    status VARCHAR(20) DEFAULT '借阅中',
    fine_amount DECIMAL(10,2) DEFAULT 0.00,
    operator_id INT,
    FOREIGN KEY (book_id) REFERENCES Books(book_id),
    FOREIGN KEY (operator_id) REFERENCES Administrators(admin_id)
);

-- 创建欠费表
CREATE TABLE IF NOT EXISTS Fees (
    fee_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id VARCHAR(50) NOT NULL,
    user_type VARCHAR(20) NOT NULL,
    record_id INT,
    fee_type VARCHAR(50) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    due_date DATE NOT NULL,
    pay_date DATE,
    status VARCHAR(20) DEFAULT '未缴',
    FOREIGN KEY (record_id) REFERENCES BorrowRecords(record_id)
);

-- 插入初始管理员账号
INSERT INTO Administrators (username, password, name, role, phone, email, register_time)
VALUES
('admin1', '123456', '超级管理员', '超级管理员', '12345678901', 'admin1@example.com', NOW()),
('admin2', '123456', '普通管理员', '普通管理员', '12345678902', 'admin2@example.com', NOW()),
('manager', '123456', '普通管理员', '普通管理员', '12345678903', 'manager@example.com', NOW())
ON DUPLICATE KEY UPDATE username=username;

-- 插入测试图书分类
INSERT INTO Categories (category_name, description)
VALUES
('文学', '文学类图书'),
('科技', '科技类图书'),
('历史', '历史类图书')
ON DUPLICATE KEY UPDATE category_name=category_name;

-- 插入测试出版社
INSERT INTO Publishers (publisher_name, address, phone, email)
VALUES
('人民教育出版社', '北京市东城区', '010-12345678', 'contact@pep.com.cn'),
('机械工业出版社', '北京市西城区', '010-87654321', 'contact@cmpedu.com')
ON DUPLICATE KEY UPDATE publisher_name=publisher_name;

-- 插入测试图书
INSERT INTO Books (book_id, title, author, category_id, publisher_id, total_count, available_count, status)
VALUES
('B001', 'Java编程思想', 'Bruce Eckel', 2, 2, 10, 10, '在架'),
('B002', '红楼梦', '曹雪芹', 1, 1, 5, 5, '在架'),
('B003', '中国通史', '吕思勉', 3, 1, 3, 3, '在架')
ON DUPLICATE KEY UPDATE title=title;

-- 插入测试欠费记录
INSERT INTO Fees (user_id, user_type, fee_type, amount, due_date, status)
VALUES
('admin1', 'admin', '逾期罚款', 50.00, '2024-01-15', '未缴'),
('admin2', 'admin', '损坏赔偿', 100.00, '2024-01-20', '未缴')
ON DUPLICATE KEY UPDATE user_id=user_id; 