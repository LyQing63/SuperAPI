package com.github.lyqing63.superapi.auth.domain.request;

import com.alibaba.dts.shade.org.codehaus.jackson.map.annotate.JsonSerialize;
import com.alibaba.dts.shade.org.codehaus.jackson.map.ser.std.ToStringSerializer;
import com.github.lyqing63.superapi.auth.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    private String id;
    @Size(min = 2, max = 20, message = "用户名长度必须在 2 到 20 个字符之间")
    private String username;
    private String avatar;
    @Email(message = "邮件格式不对")
    private String email;
    @Size(max = 11, min = 11, message = "不是合法电话号")
    private String phone;
    @JsonSerialize(using = ToStringSerializer.class)
    private BigDecimal balance;

    private static final long serialVersionUID = 1L;

}
