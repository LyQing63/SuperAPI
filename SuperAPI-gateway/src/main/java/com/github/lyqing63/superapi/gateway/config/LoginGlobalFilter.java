package com.github.lyqing63.superapi.gateway.config;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import com.github.lyqing63.superapi.gateway.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoginGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 获取请求路径
        String path = exchange.getRequest().getURI().getPath();
        // 如果路径以 "auth" 开头，跳过鉴权
        if (!path.startsWith("/api/auth")) {
            String token = exchange.getRequest().getQueryParams().getFirst("authToken");
            //返回401状态码和提示信息
            if (StringUtils.isBlank(token)) {
                return ResponseUtils.error(exchange, HttpStatus.FORBIDDEN, "鉴权失败");
            }
            // 验证Token
            JWT jwt = JWTUtil.parseToken(token);
            if (!jwt.validate(0)) {
                return ResponseUtils.error(exchange, HttpStatus.FORBIDDEN, "token过期");
            }
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
