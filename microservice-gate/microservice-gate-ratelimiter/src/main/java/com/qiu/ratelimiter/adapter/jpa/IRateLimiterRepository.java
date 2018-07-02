package com.qiu.ratelimiter.adapter.jpa;

import com.qiu.ratelimiter.adapter.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-13
 **/
@Repository
public interface IRateLimiterRepository extends CrudRepository<Rate, String> {
}
