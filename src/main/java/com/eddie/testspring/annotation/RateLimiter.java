package com.eddie.testspring.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Eddie.Liao
 * @description: TODO
 * @date 2021/11/16 5:46 下午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    long DEFAULT_REQUEST = 10;

    @AliasFor("max") long value() default DEFAULT_REQUEST;

    @AliasFor("value") long max() default DEFAULT_REQUEST;

    String key() default "";

    long timeout() default  1;

    TimeUnit timeUnit() default TimeUnit.MINUTES;


}
