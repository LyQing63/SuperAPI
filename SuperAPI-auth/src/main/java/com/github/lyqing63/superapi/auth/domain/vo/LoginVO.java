package com.github.lyqing63.superapi.auth.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoginVO {

    private String ID;
    private String username;
    private String email;
    private String phone;
    private BigDecimal balance;
    private String token;
}
