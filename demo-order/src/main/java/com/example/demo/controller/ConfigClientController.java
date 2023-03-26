package com.example.demo.controller;

import com.example.demo.config.ConfigInfoProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by YuanJW on 2023/2/20.
 */
@RestController
public class ConfigClientController {
    @Resource
    private ConfigInfoProperties configInfoProperties;

    @GetMapping("/configInfo")
    public String getConfigInfo() {
        return configInfoProperties.getInfo();
    }

    @GetMapping("/shared")
    public String shared() {
        return configInfoProperties.getShared();
    }
}
