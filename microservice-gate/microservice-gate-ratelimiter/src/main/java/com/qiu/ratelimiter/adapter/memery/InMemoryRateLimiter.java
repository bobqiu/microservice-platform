package com.qiu.ratelimiter.adapter.memery;

import com.qiu.ratelimiter.adapter.AbstractRateLimiter;
import com.qiu.ratelimiter.adapter.Rate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-13
 **/
public class InMemoryRateLimiter extends AbstractRateLimiter {

    private Map<String, Rate> repository = new ConcurrentHashMap<>();

    @Override
    protected Rate getRate(String key) {
        return this.repository.get(key);
    }

    @Override
    protected void saveRate(Rate rate) {
        this.repository.put(String.valueOf(rate.getKey()), rate);
    }

}

