package com.github.lyqing63.superapi.auth.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lyqing63.superapi.auth.domain.request.UserQueryRequest;
import com.github.lyqing63.superapi.auth.domain.vo.UserVO;
import com.github.lyqing63.superapi.auth.service.UsersService;
import com.github.lyqing63.superapi.common.domain.BusinessException;
import com.github.lyqing63.superapi.common.domain.Code;
import com.github.lyqing63.superapi.common.domain.Result;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UsersService usersService;

    @PostMapping ("/query")
    public Result getUserById(@RequestBody UserQueryRequest userQueryRequest) {

        if (BeanUtil.isEmpty(userQueryRequest)) {
            throw new BusinessException(Code.NULL_QUERY, "查询参数为空");
        }

        IPage<UserVO> user = usersService.getUser(userQueryRequest);
        return Result.success(user);
    }

    @PostMapping("/query/balance")
    public Result getUserById(@RequestBody BigDecimal num) {
        if (ObjectUtils.isEmpty(num)) {
            throw new BusinessException(Code.NULL_QUERY, "查询参数为空");
        }
        usersService.getUserByBalance(num);
        return Result.success();
    }


}
