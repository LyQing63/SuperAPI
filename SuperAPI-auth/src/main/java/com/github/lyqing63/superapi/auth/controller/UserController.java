package com.github.lyqing63.superapi.auth.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lyqing63.superapi.auth.domain.request.UpdateUserRequest;
import com.github.lyqing63.superapi.auth.domain.request.UserQueryRequest;
import com.github.lyqing63.superapi.auth.domain.vo.UserVO;
import com.github.lyqing63.superapi.auth.service.UsersService;
import com.github.lyqing63.superapi.common.domain.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UsersService usersService;

    @PostMapping ("/query")
    public Result getUserById(@Valid @RequestBody UserQueryRequest userQueryRequest) {
        IPage<UserVO> user = usersService.getUser(userQueryRequest);
        return Result.success(user);
    }

    @PostMapping("/update")
    public Result updateUser(@Valid UpdateUserRequest updateUserRequest) {
        usersService.updateUser(updateUserRequest);
        return Result.success("更新成功");
    }


}
