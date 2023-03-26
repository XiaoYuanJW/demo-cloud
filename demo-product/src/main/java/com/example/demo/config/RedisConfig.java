package com.example.demo.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis相关配置
 * Created by YuanJW on 2023/2/15.
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {

}
