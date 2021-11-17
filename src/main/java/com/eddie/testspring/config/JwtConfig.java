package com.eddie.testspring.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 12:29 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt.config")
public class JwtConfig {
    private String key = "eddie.liao";

    private Integer ttl = 600000;

    private Integer remember = 604800000;
}
