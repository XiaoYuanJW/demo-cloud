package com.example.demo.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Created by YuanJW on 2023/2/23.
 */
@Order(0)
@Component
public class AuthorizeFilter implements GlobalFilter {
    /**
     * 处理当前请求，必要的话将GatewayFilterChain将请求交给下个过滤器处理
     * @param exchange 请求上下文，用于获取Request、Response等信息
     * @param chain 过滤器链，用于将请求委托给下一个过滤器
     * @return 返回标识当前过滤器业务结束
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求ServerHttpRequest对象
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求参数
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        // 获取请求参数中的 authorization 参数
        String authorization = queryParams.getFirst("authorization");
        if ("admin".equals(authorization)) {
            // 放行
            return chain.filter(exchange);
        }
        // 拦截
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }
}
