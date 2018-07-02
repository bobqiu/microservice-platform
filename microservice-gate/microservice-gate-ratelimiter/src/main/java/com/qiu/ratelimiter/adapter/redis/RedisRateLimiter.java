package com.qiu.ratelimiter.adapter.redis;

import com.qiu.ratelimiter.adapter.Rate;
import com.qiu.ratelimiter.adapter.RateLimiter;
import com.qiu.ratelimiter.properties.RateLimitProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-13
 **/
@RequiredArgsConstructor
public class RedisRateLimiter implements RateLimiter {
    private final RedisTemplate template;

    @Override
    @SuppressWarnings("unchecked")
    public Rate consume(final RateLimitProperties.Policy policy, final String key) {
        final Long limit = policy.getLimit();
        final Long refreshInterval = policy.getRefreshInterval();
        final Long current = this.template.boundValueOps(key).increment(1L);
        Long expire = this.template.getExpire(key);
        if (expire == null || expire == -1) {
            this.template.expire(key, refreshInterval, SECONDS);
            expire = refreshInterval;
        }
        return new Rate(key, Math.max(-1, limit - current), SECONDS.toMillis(expire), null);
    }
}

