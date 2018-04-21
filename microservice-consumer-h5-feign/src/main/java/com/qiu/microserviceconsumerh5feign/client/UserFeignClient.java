package com.qiu.microserviceconsumerh5feign.client;

import com.qiu.microserviceconsumerh5feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-19
 **/

@FeignClient(value = "MICROSERVICE-PROVIDER-USERSERVICE") //
public interface UserFeignClient {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    List<User> list();
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
   User get(@PathVariable("id") String id);

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    User login(@RequestParam("name")  String name, @RequestParam("password") String password);

}
