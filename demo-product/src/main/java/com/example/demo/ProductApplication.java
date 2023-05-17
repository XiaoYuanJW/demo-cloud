package com.example.demo;

import com.example.demo.config.FeignClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by YuanJW on 2023/2/15.
 */
@EnableFeignClients(defaultConfiguration = FeignClientConfig.class, basePackages = "com.example.demo.feign")
@EnableDiscoveryClient
@SpringBootApplication
public class ProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
