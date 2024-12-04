package com.github.lyqing63.superapi.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.github.lyqing63.superapi.auth.domain.User;
import com.github.lyqing63.superapi.auth.domain.request.*;
import com.github.lyqing63.superapi.auth.domain.vo.LoginVO;
import com.github.lyqing63.superapi.auth.domain.vo.UserVO;

/**
* @author yjxx_2022
* @description 针对表【users】的数据库操作Service
* @createDate 2024-11-29 16:47:09
*/
public interface UsersService extends IService<User> {

    Boolean register(RegisterUserRequest registerUserVO);

    LoginVO login(LoginUserRequest loginUserVO);

    UserVO getLoginUser(UserInfoRequest userInfoRequest);

    IPage<UserVO> getUser(UserQueryRequest userQueryRequest);

    Boolean updateUser(UpdateUserRequest updateUserRequest);

}
