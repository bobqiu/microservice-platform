package com.qiu.microserviceconsumerh5.controller;

import com.qiu.microserviceconsumerh5.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
@RestController
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("user/get/{id}")
    public User get(@PathVariable Long id){
        System.out.println("=====================this is test==============================");
        return this.restTemplate.getForObject("http://127.0.0.1:9996/get/{1}",User.class,id);
    }
}
