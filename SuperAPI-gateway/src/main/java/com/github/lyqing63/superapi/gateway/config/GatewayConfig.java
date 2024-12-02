package com.github.lyqing63.superapi.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class GatewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("superapi-auth-router", r -> r.path("/api/**")
                        .filters(f -> f.stripPrefix(0)
                                .modifyResponseBody(String.class, String.class,
                                        (exchange, s) -> {
                                            //TODO: 此处可以获取返回体的所有信息
                                            System.out.println(s);
                                            return Mono.just(s);
                                        })).uri("http://localhost:8101"))
                .build();
    }

}
