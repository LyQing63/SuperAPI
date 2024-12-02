package com.github.lyqing63.superapi.auth.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.UUID;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.signers.JWTSigner;
import cn.hutool.jwt.signers.JWTSignerUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.lyqing63.superapi.auth.common.LoginType;
import com.github.lyqing63.superapi.auth.domain.User;
import com.github.lyqing63.superapi.auth.domain.dto.LoginUserDTO;
import com.github.lyqing63.superapi.auth.domain.dto.RegisterUserDTO;
import com.github.lyqing63.superapi.auth.domain.vo.LoginVO;
import com.github.lyqing63.superapi.auth.domain.vo.UserVO;
import com.github.lyqing63.superapi.auth.mapper.UsersMapper;
import com.github.lyqing63.superapi.auth.service.UsersService;
import com.github.lyqing63.superapi.auth.utils.UserUtils;
import com.github.lyqing63.superapi.common.domain.BusinessException;
import com.github.lyqing63.superapi.common.domain.Code;
import com.github.lyqing63.superapi.utils.utils.PasswordEncoder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
* @author yjxx_2022
* @description 针对表【users】的数据库操作Service实现
* @createDate 2024-11-29 16:47:09
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, User>
    implements UsersService{

    @Resource
    private UsersMapper usersMapper;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Boolean register(RegisterUserDTO registerUserVO) {
        int res = usersMapper.insert(UserUtils.registerUserVO2Users(registerUserVO));
        return res > 0;
    }

    @Override
    public LoginVO login(LoginUserDTO loginUserVO) {
        String type = loginUserVO.getType();
        User user = null;
        if (LoginType.EMAIL.equals(type)) {
            user = loginWithEmail(loginUserVO);
        } else if (LoginType.GOOGLE.equals(type)) {
            loginWithGoogle(loginUserVO);
        } else if (LoginType.GITHUB.equals(type)) {
            loginWithGithub(loginUserVO);
        } else {
            throw new BusinessException(Code.BAD_LOGIN_TYPE, "登录类型有误");
        }

        //使用jwt工具生成token
        String token = createToken(user);
        // 成功更新last_login_at字段
        user.setLastLoginAt(new Date());
        this.saveOrUpdate(user);

        LoginVO loginVO = new LoginVO();
        loginVO.setID(user.getId());
        loginVO.setToken(token);
        loginVO.setPhone(user.getPhone());
        loginVO.setEmail(user.getEmail());
        loginVO.setBalance(user.getBalance());
        return loginVO;
    }

    @Override
    public UserVO getLoginUser(String token) {

        JWT jwt = JWTUtil.parseToken(token);
        // 判断token是否过期
        boolean validate = jwt.validate(0);
        if (!validate) {
            throw new BusinessException(Code.TOKEN_NOT_VALIDATE, "token过期");
        }
        String id = jwt.getPayload("ID").toString();
        User user = usersMapper.selectById(id);
        UserVO userVO = UserUtils.user2UserVO(user);
        return userVO;
    }

    private String createToken(User user) {
        DateTime now = DateTime.now();
        // 一个月过期
        DateTime loseTime = now.offset(DateField.DAY_OF_MONTH, 1);
        Map<String, Object> payload = new HashMap<>(4);
        payload.put("ID", user.getId());
        payload.put(JWTPayload.ISSUED_AT, now);
        payload.put(JWTPayload.EXPIRES_AT, loseTime);
        payload.put(JWTPayload.NOT_BEFORE, now);
        JWTSigner jwtSigner = JWTSignerUtil.hs512("lyqing63".getBytes(StandardCharsets.UTF_8));
        String token = JWTUtil.createToken(payload, jwtSigner);

        // 存入分布式redis中
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("login:"+token, user.getEmail());
        // 随机，防止缓存雪崩
        // 随机0-60s
        redisTemplate.expire("login:"+token, 30 * 24 * 60 * 60 + new Random().nextInt(60), TimeUnit.SECONDS);

        return token;
    }


    private User loginWithGoogle(LoginUserDTO loginUserVO) {
//        usersService.loginGoogle(loginUserVO);
        return null;
    }

    private User loginWithGithub(LoginUserDTO loginUserVO) {
//        usersService.loginWithGithub(loginUserVO);
        return null;
    }

    public User loginWithEmail(LoginUserDTO loginUserVO) {
        String email = loginUserVO.getEmail();
        User user = lambdaQuery().eq(User::getEmail, email).one();
        if (BeanUtil.isEmpty(user)) {
            throw new BusinessException(Code.WRONG_EMAIL, "该邮箱未注册");
        }

        // 判断是否被冻结
        if (user.getStatus() == "SUSPENDED") {
            throw new BusinessException(Code.UNAUTHORIZED_USER, "账户已被冻结");
        }

        // 判断密码
        if (!PasswordEncoder.matches(loginUserVO.getPassword(), user.getPassword())) {
            throw new BusinessException(Code.WRONG_PASSWORD, "密码错误");
        }
        return user;
    }





}




