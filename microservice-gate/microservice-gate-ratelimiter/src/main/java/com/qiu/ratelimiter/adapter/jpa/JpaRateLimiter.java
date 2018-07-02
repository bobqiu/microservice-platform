package com.qiu.ratelimiter.adapter.jpa;

import com.qiu.ratelimiter.adapter.AbstractRateLimiter;
import com.qiu.ratelimiter.adapter.Rate;
import lombok.RequiredArgsConstructor;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-13
 **/
@RequiredArgsConstructor
public class JpaRateLimiter extends AbstractRateLimiter {
    private final IRateLimiterRepository repository;

    @Override
    protected Rate getRate(String key) {
        return this.repository.findById(key).get();
    }

    @Override
    protected void saveRate(Rate rate) {
        this.repository.save(rate);
    }
}
