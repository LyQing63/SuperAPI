CREATE DATABASE IF NOT EXISTS super_api;

USE super_api;

-- 构建用户表
DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id CHAR(36) PRIMARY KEY,  -- 用户ID
  username VARCHAR(255) NOT NULL UNIQUE,  -- 用户名
  email VARCHAR(255) NOT NULL UNIQUE,  -- 用户邮箱
  password VARCHAR(255) NOT NULL,  -- 密码
  phone VARCHAR(20),  -- 用户手机号
  status ENUM('ACTIVE', 'INACTIVE', 'SUSPENDED') DEFAULT 'ACTIVE',  -- 用户状态
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
  deleted TINYINT DEFAULT 0, -- 是否被删除
  last_login_at TIMESTAMP,  -- 最后登录时间
  subscription_status ENUM('ACTIVE', 'EXPIRED', 'CANCELLED') DEFAULT 'ACTIVE',  -- 用户订阅状态
  balance DECIMAL(10, 2) DEFAULT 0.00  -- 账户余额
);

-- 构建订阅表
DROP TABLE IF EXISTS subscription_plans;
CREATE TABLE subscription_plans (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 套餐ID
  name VARCHAR(255) NOT NULL,  -- 套餐名称（如：基础版、专业版、企业版等）
  price DECIMAL(10, 2) NOT NULL,  -- 套餐价格
  api_call_limit INT DEFAULT 1000,  -- 每月允许的API调用次数
  duration ENUM('MONTH', 'YEAR') DEFAULT 'MONTH',  -- 套餐有效期（月度或年度）
  description TEXT,  -- 套餐描述
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP  -- 更新时间
);

-- 构建用户订阅表
DROP TABLE IF EXISTS user_subscriptions;
CREATE TABLE user_subscriptions (
  id CHAR(36) PRIMARY KEY,  -- 订阅ID
  user_id CHAR(36) NOT NULL,  -- 用户ID，外键关联用户表
  plan_id BIGINT NOT NULL,  -- 套餐ID，外键关联订阅计划表
  start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 订阅开始时间
  end_date TIMESTAMP,  -- 订阅结束时间
  status ENUM('ACTIVE', 'EXPIRED', 'CANCELLED') DEFAULT 'ACTIVE',  -- 订阅状态
  next_billing_date TIMESTAMP,  -- 下次账单生成时间
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,  -- 外键约束
  FOREIGN KEY (plan_id) REFERENCES subscription_plans(id) ON DELETE CASCADE  -- 外键约束
);

-- 构建API调用日志表
DROP TABLE IF EXISTS api_call_logs;
CREATE TABLE api_call_logs (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- 调用日志ID
  user_id CHAR(36) NOT NULL,  -- 用户ID，外键关联用户表
  api_endpoint VARCHAR(255) NOT NULL,  -- API接口路径
  request_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 请求时间
  status ENUM('SUCCESS', 'FAILURE') DEFAULT 'SUCCESS',  -- 调用状态
  response_time INT,  -- 响应时间（单位：毫秒）
  error_message TEXT,  -- 错误消息（如果有）
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE  -- 外键约束
);

-- 构建支付记录表
DROP TABLE IF EXISTS payment_records;
CREATE TABLE payment_records (
 id CHAR(36) PRIMARY KEY,  -- 支付记录ID
 user_id CHAR(36) NOT NULL,  -- 用户ID，外键关联用户表
 amount DECIMAL(10, 2) NOT NULL,  -- 支付金额
 payment_method ENUM('ALIPAY', 'WECHAT') NOT NULL,  -- 支付方式
 payment_status ENUM('SUCCESS', 'FAILED', 'PENDING') DEFAULT 'PENDING',  -- 支付状态
 payment_time TIMESTAMP,  -- 支付时间
 transaction_id VARCHAR(255),  -- 支付交易号
 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
 FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE  -- 外键约束
);

-- 构建账单表
DROP TABLE IF EXISTS invoices;
CREATE TABLE invoices (
  id CHAR(36) PRIMARY KEY,  -- 账单ID
  user_id CHAR(36) NOT NULL,  -- 用户ID，外键关联用户表
  amount DECIMAL(10, 2) NOT NULL,  -- 账单金额
  invoice_status ENUM('PENDING', 'PAID', 'CANCELLED') DEFAULT 'PENDING',  -- 账单状态
  due_date TIMESTAMP,  -- 账单到期日期
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE  -- 外键约束
);

-- 构建总账表
DROP TABLE IF EXISTS general_ledger;
CREATE TABLE general_ledger (
  id CHAR(36) PRIMARY KEY,  -- 记录ID
  user_id CHAR(36) NOT NULL,  -- 用户ID，外键关联用户表
  amount DECIMAL(10, 2) NOT NULL,  -- 金额
  transaction_type ENUM('DEPOSIT', 'WITHDRAWAL', 'CHARGEBACK', 'REFUND') NOT NULL,  -- 交易类型
  transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 交易时间
  balance_after DECIMAL(10, 2),  -- 交易后的账户余额
  description TEXT,  -- 交易描述
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE  -- 外键约束
);

-- 构建API密钥表
DROP TABLE IF EXISTS api_keys;
CREATE TABLE api_keys (
  id CHAR(36) PRIMARY KEY,  -- 密钥ID
  user_id CHAR(36) NOT NULL,  -- 用户ID，外键关联用户表
  api_key VARCHAR(255) NOT NULL UNIQUE,  -- API密钥，通常是一个加密字符串
  secret_key VARCHAR(255) NOT NULL,  -- 密钥的秘密部分，通常保存在服务器端
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 创建时间
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,  -- 更新时间
  status ENUM('ACTIVE', 'INACTIVE', 'REVOKED') DEFAULT 'ACTIVE',  -- 密钥状态
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE  -- 外键约束
);
