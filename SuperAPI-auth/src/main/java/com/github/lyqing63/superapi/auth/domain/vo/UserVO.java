package com.github.lyqing63.superapi.auth.domain.vo;

import com.alibaba.dts.shade.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.alibaba.dts.shade.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserVO {

    private String id;
    private String username;
    private String avatar;
    private String email;
    private String phone;
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal balance;

}
