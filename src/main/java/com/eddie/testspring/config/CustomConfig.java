package com.eddie.testspring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 12:21 下午
 */
@Component
@ConfigurationProperties(prefix = "custom.config")
@Data
public class CustomConfig {
    private IgnoreConfig ignores;
}

