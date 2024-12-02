package com.github.lyqing63.superapi.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableRedisHttpSession
@MapperScan("com.github.lyqing63.superapi.auth.mapper")
public class SuperApiAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperApiAuthApplication.class, args);
    }

}
