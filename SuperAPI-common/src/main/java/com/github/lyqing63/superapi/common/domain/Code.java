package com.github.lyqing63.superapi.common.domain;

public class Code {

    // 通用的成功与失败状态码
    public static final int SUCCESS = 200;  // 成功
    public static final int ERROR = 500;  // 服务器错误

    // 登录注册相关
    public static final int NULL_REQUEST = 40000;  // 请求为空
    public static final int NULL_USERNAME = 40001;  // 用户名为空
    public static final int NULL_PASSWORD = 40003;  // 用户密码为空
    public static final int NULL_CONFIRM_PASSWORD = 40005;  // 确认密码为空
    public static final int NULL_EMAIL = 40002;  // 用户邮箱为空
    public static final int BAD_CONFIRM_PASSWORD = 40004;  // 确认密码与密码不同
    public static final int NULL_LOGIN_TYPE = 40006;  // 登录类型为空
    public static final int BAD_LOGIN_TYPE = 40007;  // 登录类型有误
    public static final int WRONG_PASSWORD = 40009;  // 密码有误
    public static final int WRONG_EMAIL = 40008;  // 这个邮箱未注册

    public static final int UNAUTHORIZED = 40100;  // 未授权
    public static final int UNAUTHORIZED_USER = 40101;  // 被冻结

    public static final int FORBIDDEN = 403;     // 禁止访问
    public static final int NOT_FOUND = 404;     // 资源未找到
    public static final int BAD_REQUEST = 400;   // 错误请求
    public static final int VALIDATION_ERROR = 422; // 验证失败

    // 其他常见业务错误码
    public static final int ACCOUNT_LOCKED = 1001; // 账户锁定
    public static final int INVALID_CREDENTIALS = 1002; // 无效凭证

    // 定义状态码和消息的映射
    public static final String MSG_SUCCESS = "操作成功";
    public static final String MSG_FAILURE = "服务器错误";
    public static final String MSG_UNAUTHORIZED = "未授权";
    public static final String MSG_FORBIDDEN = "禁止访问";
    public static final String MSG_NOT_FOUND = "资源未找到";
    public static final String MSG_BAD_REQUEST = "错误请求";
    public static final String MSG_VALIDATION_ERROR = "验证失败";
    public static final String MSG_ACCOUNT_LOCKED = "账户已锁定";
    public static final String MSG_INVALID_CREDENTIALS = "无效凭证";

    // 你也可以根据需要增加更多的常量定义
}