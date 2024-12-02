package com.github.lyqing63.superapi.auth.domain.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoginRequest {

    private String ID;
    private String username;
    private String email;
    private String phone;
    private BigDecimal balance;
    private String token;
}
