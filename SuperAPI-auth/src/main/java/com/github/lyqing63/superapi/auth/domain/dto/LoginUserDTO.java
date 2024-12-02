package com.github.lyqing63.superapi.auth.domain.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginUserDTO {

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
    private String type;

}
