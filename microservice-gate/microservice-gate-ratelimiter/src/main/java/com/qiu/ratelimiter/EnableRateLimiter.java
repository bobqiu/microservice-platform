package com.qiu.ratelimiter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author bobqiu
 * @descripe:
 * @create 2018/6/13
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RateLimitAutoConfiguration.class)
@Documented
@Inherited
public @interface EnableRateLimiter {
}

