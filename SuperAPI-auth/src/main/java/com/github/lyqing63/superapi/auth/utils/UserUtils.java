package com.github.lyqing63.superapi.auth.utils;
import java.math.BigDecimal;

import cn.hutool.core.lang.UUID;
import com.github.lyqing63.superapi.auth.domain.User;
import com.github.lyqing63.superapi.auth.domain.request.RegisterUserRequest;
import com.github.lyqing63.superapi.auth.domain.request.UpdateUserRequest;
import com.github.lyqing63.superapi.auth.domain.vo.UserVO;
import com.github.lyqing63.superapi.utils.utils.PasswordEncoder;

public class UserUtils {

    public static UserVO user2UserVO(User user) {
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setAvatar(user.getAvatar());
        userVO.setEmail(user.getEmail());
        userVO.setPhone(user.getPhone());
        userVO.setBalance(user.getBalance());
        return userVO;
    }

    /**
     * 将RegisterUserVO转化未User
     *
     * @param registerUserVO
     * @return User
     */
    public static User registerUserRequest2Users(RegisterUserRequest registerUserVO) {
        // 通过使用UUID来构建userid字段，防止多个数据库
        String id = UUID.randomUUID().toString();
        User user = new User();
        user.setId(id);
        user.setUsername(registerUserVO.getUsername());
        // 加密密码
        String encodePassword = PasswordEncoder.encode(registerUserVO.getPassword());
        user.setPassword(encodePassword);
        user.setEmail(registerUserVO.getEmail());
        user.setPhone(registerUserVO.getPhone());
        return user;
    }

    public static User updateUserRequest2Users(UpdateUserRequest updateUserRequest) {
        String id = updateUserRequest.getId();
        String username = updateUserRequest.getUsername();
        String email = updateUserRequest.getEmail();
        String password = updateUserRequest.getPassword();
        String avatar = updateUserRequest.getAvatar();
        String phone = updateUserRequest.getPhone();
        Object status = updateUserRequest.getStatus();
        Object subscriptionStatus = updateUserRequest.getSubscriptionStatus();
        BigDecimal balance = updateUserRequest.getBalance();

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setAvatar(avatar);
        user.setPhone(phone);
        user.setStatus(status);
        user.setSubscriptionStatus(subscriptionStatus);
        user.setBalance(balance);
        return user;
    }

}
