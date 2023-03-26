package com.example.demo.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * Feign自定义配置
 * Created by YuanJW on 2023/2/22.
 */
public class FeignClientConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
