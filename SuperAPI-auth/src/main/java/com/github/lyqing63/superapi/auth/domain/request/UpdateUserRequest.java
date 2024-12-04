package com.github.lyqing63.superapi.auth.domain.request;

import com.alibaba.dts.shade.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.alibaba.dts.shade.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class UpdateUserRequest implements Serializable {

    @NotBlank
    private String id;

    private String username;

    @Email
    private String email;

    private String password;

    private String avatar;

    @Size(max = 11, min = 11, message = "不是合法电话号")
    private String phone;

    private Object status;

    private Object subscriptionStatus;

    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal balance;

    private static final long serialVersionUID = 1L;
}
