package com.qiu.microserviceconsumerh5feign.client;

import com.qiu.microserviceconsumerh5feign.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-27
 **/
@Service
public class UserServiceFallback implements UserFeignClient {
    @Override
    public List<User> list() {
        return null;
    }

    @Override
    public User get(String id) {
        return null;
    }

    @Override
    public User login(String name, String password) {
        return null;
    }
}
