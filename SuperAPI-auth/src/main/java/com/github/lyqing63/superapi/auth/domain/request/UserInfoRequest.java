package com.github.lyqing63.superapi.auth.domain.request;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class UserInfoRequest implements Serializable {

    @NotBlank
    private String token;

    private static final long serialVersionUID = 1L;
}
