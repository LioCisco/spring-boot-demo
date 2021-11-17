package com.eddie.testspring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 4:44 下午
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static final long MAX_AGE_SECS = 3600;
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE").maxAge(MAX_AGE_SECS);
    }
}
