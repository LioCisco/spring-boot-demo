package com.eddie.testspring.aspectj;


import cn.hutool.core.util.StrUtil;
import com.eddie.testspring.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Component;


import java.lang.reflect.Method;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/16 5:50 下午
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RateLimiterAspect {

    private final static String SEPARATOR = ":";

    private final static String REDIS_LIMIT_KEY_PREFIX = "limit:";

    private final StringRedisTemplate stringRedisTemplate;

    private final RedisScript<Long> limitRedisScript;

    @Pointcut("@annotation(com.eddie.testspring.annotation.RateLimiter)")
    public void rateLimit(){

    }

    @Around("rateLimit()")
    public Object pointcut(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RateLimiter rateLimiter = AnnotationUtils.findAnnotation(method,RateLimiter.class);


        if(rateLimiter != null){
            String key = rateLimiter.key();
            if(StrUtil.isBlank(key)){
                key = method.getDeclaringClass().getName() + StrUtil.DOT +method.getName();
            }

            key = key+ SEPARATOR + com.xkcoding.ratelimit.redis.util.IpUtil.getIpAddr();
            long max = rateLimiter.max();
            long timeout = rateLimiter.timeout();
            TimeUnit timeUnit = rateLimiter.timeUnit();
            boolean limited = shouldLimited(key,max,timeout,timeUnit);
            if(limited){
//                throw new RuntimeException("手速太快了，慢点儿吧～");
                return "手速太快了，慢点儿吧～";
            }
        }

        return point.proceed();
    }


    private boolean shouldLimited(String key,long max,long timeout,TimeUnit timeUnit){
        key = REDIS_LIMIT_KEY_PREFIX + key;

        long ttl = timeUnit.toMillis(timeout);

        long now = Instant.now().toEpochMilli();

        long expired = now - ttl ;

        Long executeTimes = stringRedisTemplate.execute(limitRedisScript, Collections.singletonList(key),now+"",ttl+"",expired+"",max+"");

        if (executeTimes != null) {
            if (executeTimes == 0) {
                log.error("【{}】在单位时间 {} 毫秒内已到达访问上限 {}",key,ttl,max);
                return true;
            }else{
                log.info("【{}】在单位时间 {} 毫秒内访问 {} 次", key, ttl, executeTimes);
                return false;
            }
        }
        return false;
    }
}
