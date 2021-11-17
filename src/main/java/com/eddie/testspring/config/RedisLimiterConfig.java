package com.eddie.testspring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/17 10:56 上午
 */
@Configuration
public class RedisLimiterConfig {
    @Bean
    @SuppressWarnings("unchecked")
    public RedisScript<Long> limitRedisScript() {
        DefaultRedisScript redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/lua/limit.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }
}
