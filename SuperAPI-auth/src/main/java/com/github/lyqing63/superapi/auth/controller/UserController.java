package com.github.lyqing63.superapi.auth.controller;

import com.github.lyqing63.superapi.auth.domain.vo.UserVO;
import com.github.lyqing63.superapi.auth.service.UsersService;
import com.github.lyqing63.superapi.common.domain.BusinessException;
import com.github.lyqing63.superapi.common.domain.Code;
import com.github.lyqing63.superapi.common.domain.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UsersService usersService;



}
