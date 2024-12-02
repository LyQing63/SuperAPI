package com.github.lyqing63.superapi.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lyqing63.superapi.auth.domain.User;
import com.github.lyqing63.superapi.auth.domain.dto.LoginUserDTO;
import com.github.lyqing63.superapi.auth.domain.dto.RegisterUserDTO;
import com.github.lyqing63.superapi.auth.domain.request.LoginRequest;
import com.github.lyqing63.superapi.auth.domain.request.UserQueryRequest;
import com.github.lyqing63.superapi.auth.domain.vo.UserVO;

import java.math.BigDecimal;

/**
* @author yjxx_2022
* @description 针对表【users】的数据库操作Service
* @createDate 2024-11-29 16:47:09
*/
public interface UsersService extends IService<User> {

    Boolean register(RegisterUserDTO registerUserVO);

    LoginRequest login(LoginUserDTO loginUserVO);

    UserVO getLoginUser(String token);

    IPage<UserVO> getUser(UserQueryRequest userQueryRequest);

    IPage<UserVO> getUserByBalance(BigDecimal num);
}
