package com.qiu.microserviceconsumerh5feign.controller;

import com.qiu.microserviceconsumerh5feign.client.UserFeignClient;
import com.qiu.microserviceconsumerh5feign.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-19
 **/
@RestController
public class UserFeignController {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserFeignController.class);


    @Autowired
    UserFeignClient userFeignClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping(value = "/list")
    public List<User> list(){

        return userFeignClient.list();
    }
    @RequestMapping(value = "/user/get/{id}",method = RequestMethod.GET)
    User get(@PathVariable("id") String id){
        return userFeignClient.get(id);
    }

    @GetMapping("/login")
    public User login(@RequestParam String name, @RequestParam String password){

        return userFeignClient.login(name,password);
    }

    /**
     * ribbon负载均衡测试方法
     * 将feign和ribbon以及eureka进行了集成
     */
    @GetMapping("/log-user-service-instance")
    public void loguserserviceinstance(){

        ServiceInstance serviceInstance=this.loadBalancerClient.choose("microservice-provider-userservice");

        LOGGER.info("=====================serviceInstance info ---> serviceId is  "+serviceInstance.getServiceId()+" host is "+serviceInstance.getHost()+"port is "+serviceInstance.getPort() );
    }

}
