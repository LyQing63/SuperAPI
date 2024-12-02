package com.github.lyqing63.superapi.auth.controller;

import cn.hutool.core.bean.BeanUtil;
import com.github.lyqing63.superapi.auth.domain.dto.LoginUserDTO;
import com.github.lyqing63.superapi.auth.domain.dto.RegisterUserDTO;
import com.github.lyqing63.superapi.auth.domain.request.LoginRequest;
import com.github.lyqing63.superapi.auth.domain.vo.UserVO;
import com.github.lyqing63.superapi.auth.service.UsersService;
import com.github.lyqing63.superapi.common.domain.BusinessException;
import com.github.lyqing63.superapi.common.domain.Code;
import com.github.lyqing63.superapi.common.domain.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    public Result auth(@RequestBody LoginUserDTO loginUserVO) {

        if (BeanUtil.isEmpty(loginUserVO)) {
            throw new BusinessException(Code.NULL_REQUEST, "请求参数为空");
        }

        if (StringUtils.isAnyBlank(loginUserVO.getPassword())) {
            throw new BusinessException(Code.NULL_PASSWORD, "密码为空");
        }

        if (StringUtils.isAnyBlank(loginUserVO.getEmail())) {
            throw new BusinessException(Code.NULL_EMAIL, "邮箱为空");
        }

        if (StringUtils.isAnyBlank(loginUserVO.getType())) {
            throw new BusinessException(Code.NULL_LOGIN_TYPE, "登录类型为空");
        }

        LoginRequest loginVO = usersService.login(loginUserVO);
        return Result.success(loginVO);
    }

    @PostMapping("/register")
    public Result register(@RequestBody RegisterUserDTO registerUserVO) {

        if (BeanUtil.isEmpty(registerUserVO)) {
            throw new BusinessException(Code.NULL_REQUEST, "请求为空");
        }

        if (StringUtils.isAnyBlank(registerUserVO.getUsername())) {
            throw new BusinessException(Code.NULL_USERNAME, "用户名为空");
        }

        if (StringUtils.isAnyBlank(registerUserVO.getPassword())) {
            throw new BusinessException(Code.NULL_PASSWORD, "密码为空");
        }

        if (StringUtils.isAnyBlank(registerUserVO.getConfirmPassword())) {
            throw new BusinessException(Code.NULL_CONFIRM_PASSWORD, "确认密码为空");
        }

        if (StringUtils.isAnyBlank(registerUserVO.getEmail())) {
            throw new BusinessException(Code.NULL_EMAIL, "邮箱为空");
        }

        // 两次密码输入相同
        if (!registerUserVO.getPassword().equals(registerUserVO.getConfirmPassword())) {
            throw new BusinessException(Code.BAD_CONFIRM_PASSWORD, "两次密码输入不相同");
        }

        usersService.register(registerUserVO);

        return Result.success("注册成功!");
    }

    @GetMapping("/info")
    public Result info(@RequestParam String token) {

        // 判断token是否为空
        if (StringUtils.isAnyBlank(token)) {
            throw new BusinessException(Code.NULL_TOKEN, "token为空");
        }

        UserVO loginUser = usersService.getLoginUser(token);

        return Result.success(loginUser);
    }

}
