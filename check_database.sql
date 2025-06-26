-- 检查数据库是否存在
SHOW DATABASES LIKE 'library_db';

-- 使用数据库
USE library_db;

-- 检查表是否存在
SHOW TABLES;

-- 检查Fees表结构
DESCRIBE Fees;

-- 检查BorrowRecords表结构
DESCRIBE BorrowRecords;

-- 检查Fees表中的数据
SELECT * FROM Fees;

-- 检查BorrowRecords表中的数据
SELECT * FROM BorrowRecords; 