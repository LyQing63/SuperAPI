package com.github.lyqing63.superapi.auth.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserVO {

    private String id;
    private String username;
    private String avatar;
    private String email;
    private String phone;
    private BigDecimal balance;

}
