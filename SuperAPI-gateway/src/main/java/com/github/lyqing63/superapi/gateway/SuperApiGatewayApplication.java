package com.github.lyqing63.superapi.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SuperApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperApiGatewayApplication.class, args);
    }

}
