package com.github.lyqing63.superapi.gateway.config;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.charset.StandardCharsets;

/**
 * 记录转发的地址，并且做统一鉴权
 *
 * @author LyQing63
 */
@Configuration
@Slf4j
public class RouteRecordGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // 获取请求路径
        String path = exchange.getRequest().getURI().getPath();
        URI proxyRequestUri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        long start = System.currentTimeMillis();
        // 如果路径以 "auth" 开头，跳过鉴权
        if (!path.startsWith("/api/auth")) {
            String token = exchange.getRequest().getQueryParams().getFirst("authToken");
            //返回401状态码和提示信息
            if (StringUtils.isBlank(token)) {
                ServerHttpResponse response = exchange.getResponse();
                JSONObject message = new JSONObject();
                message.put("status", -1);
                message.put("data", "鉴权失败");
                byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
                DataBuffer buffer = response.bufferFactory().wrap(bits);
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                //指定编码，否则在浏览器中会中文乱码
                response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
                return response.writeWith(Mono.just(buffer));
            }
        }
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long end = System.currentTimeMillis();
            log.info("实际调用地址为：{}，调用耗时为：{}ms", proxyRequestUri, (end - start));
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }


}
