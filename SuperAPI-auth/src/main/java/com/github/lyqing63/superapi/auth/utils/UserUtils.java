package com.github.lyqing63.superapi.auth.utils;

import cn.hutool.core.lang.UUID;
import com.github.lyqing63.superapi.auth.domain.User;
import com.github.lyqing63.superapi.auth.domain.dto.RegisterUserDTO;
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
    public static User registerUserVO2Users(RegisterUserDTO registerUserVO) {
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


}
