package com.example.demo;

import com.example.demo.config.FeignClientConfig;
import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by YuanJW on 2023/2/15.
 */
@EnableFeignClients(defaultConfiguration = FeignClientConfig.class, basePackages = "com.example.demo.feign")
@EnableDiscoveryClient
@EnableAutoDataSourceProxy
@SpringBootApplication
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
