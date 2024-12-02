package com.github.lyqing63.superapi.auth.service;

import com.github.lyqing63.superapi.auth.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lyqing63.superapi.auth.domain.dto.LoginUserDTO;
import com.github.lyqing63.superapi.auth.domain.dto.RegisterUserDTO;
import com.github.lyqing63.superapi.auth.domain.vo.LoginVO;
import com.github.lyqing63.superapi.auth.domain.vo.UserVO;

/**
* @author yjxx_2022
* @description 针对表【users】的数据库操作Service
* @createDate 2024-11-29 16:47:09
*/
public interface UsersService extends IService<User> {

    Boolean register(RegisterUserDTO registerUserVO);

    LoginVO login(LoginUserDTO loginUserVO);

    UserVO getLoginUser(String token);

}
