package com.github.lyqing63.superapi.auth.interceptor;

import com.github.lyqing63.superapi.utils.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userString = request.getHeader("user");
        Optional<String> optional = Optional.ofNullable(userString);
        if(optional.isPresent()) {
            //把用户存入threadLocal中
            ThreadLocalUtils.setParameter("user", userString);
            log.info("设置用户信息到threadlocal中...");
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ThreadLocalUtils.removeParameterMap();
        log.info("清理threadlocal...");
    }
}
