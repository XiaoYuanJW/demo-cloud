package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by YuanJW on 2023/2/21.
 */
@Data
@Component
@ConfigurationProperties(prefix = "config")
public class ConfigInfoProperties {
    private String info;

    private String shared;
}
