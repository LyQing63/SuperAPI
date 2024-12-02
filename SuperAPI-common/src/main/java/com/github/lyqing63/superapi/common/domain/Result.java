package com.github.lyqing63.superapi.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    // 状态码
    private Integer code;

    // 提示信息
    private String message;

    // 返回数据
    private T data;

    // 操作是否成功
    private Boolean success;

    // 工厂方法，简化返回结果的创建
    public static <T> Result<T> success(T data) {
        return new Result<>(Code.SUCCESS, "操作成功", data, true);
    }

    public static <T> Result<T> success() {
        return new Result<>(Code.SUCCESS, "操作成功", null, true);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null, false);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(Code.ERROR, message, null, false);
    }
}
