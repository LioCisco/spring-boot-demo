package com.eddie.testspring.config;

import com.eddie.testspring.constant.Status;
import com.eddie.testspring.util.ResponseUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.nio.file.AccessDeniedException;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 4:41 下午
 */
@Configuration
public class SecurityHandleConfig {

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return (request,response, AccessDeniedException) -> ResponseUtil.renderJson(response, Status.ACCESS_DENIED,null);
    }

}
