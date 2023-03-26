package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 网关配置
 * Created by YuanJW on 2023/2/23.
 */
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator customizeRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("demo-order",
                        r -> r.path("/order/**")
                                .uri("lb://demo-order")
                ).build();
    }
}
