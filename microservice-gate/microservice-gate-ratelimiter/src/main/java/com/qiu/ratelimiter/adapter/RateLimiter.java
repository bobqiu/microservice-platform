package com.qiu.ratelimiter.adapter;

import com.qiu.ratelimiter.properties.RateLimitProperties;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-13
 **/
public interface RateLimiter {
    Rate consume(RateLimitProperties.Policy policy, String key);
}
