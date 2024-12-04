package com.github.lyqing63.superapi.auth.controller;

import com.github.lyqing63.superapi.auth.domain.request.LoginUserRequest;
import com.github.lyqing63.superapi.auth.domain.request.RegisterUserRequest;
import com.github.lyqing63.superapi.auth.domain.request.UserInfoRequest;
import com.github.lyqing63.superapi.auth.domain.vo.LoginVO;
import com.github.lyqing63.superapi.auth.domain.vo.UserVO;
import com.github.lyqing63.superapi.auth.service.UsersService;
import com.github.lyqing63.superapi.common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author LyQing63
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Resource
    private UsersService usersService;

    @PostMapping("/login")
    public Result auth(@Valid @RequestBody LoginUserRequest loginUserVO) {
        LoginVO loginVO = usersService.login(loginUserVO);
        return Result.success(loginVO);
    }

    @PostMapping("/register")
    public Result register(@Valid @RequestBody RegisterUserRequest registerUserVO) {
        usersService.register(registerUserVO);
        return Result.success("注册成功!");
    }

    @GetMapping("/info")
    public Result info(@Valid @RequestParam UserInfoRequest userInfoRequest) {
        UserVO loginUser = usersService.getLoginUser(userInfoRequest);

        return Result.success(loginUser);
    }


}
