package com.github.lyqing63.superapi.auth.domain.request;

import com.baomidou.mybatisplus.annotation.TableField;
import com.github.lyqing63.superapi.auth.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    private String id;
    private String username;
    private String avatar;
    private String email;
    private String phone;
    private BigDecimal balance;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
