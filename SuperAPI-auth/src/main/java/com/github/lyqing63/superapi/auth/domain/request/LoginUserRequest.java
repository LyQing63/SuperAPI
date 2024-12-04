package com.github.lyqing63.superapi.auth.domain.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginUserRequest implements Serializable {

    /**
     *
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     *
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 登录类别
     */
    @NotBlank(message = "请求类型不能为空")
    private String type;

    private static final long serialVersionUID = 1L;
}
