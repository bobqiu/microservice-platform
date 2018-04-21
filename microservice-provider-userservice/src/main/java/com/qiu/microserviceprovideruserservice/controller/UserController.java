package com.qiu.microserviceprovideruserservice.controller;

import com.qiu.microserviceprovideruserservice.entity.User;
import com.qiu.microserviceprovideruserservice.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
@RestController
public class UserController {



    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private DiscoveryClient discoveryClient;


    @Autowired
    private UserService userService;


    @GetMapping(value = "/list")
    public List<User> list(){

       // ServiceInstance instance=discoveryClient.getLocalServiceInstance();
      //  LOGGER.info("call user/list service  host is  "+instance.getHost()+"service_id is "+instance.getServiceId());
        return userService.getAllUser();
    }

    @GetMapping(value = "/login")
    public User login(@RequestParam String name, @RequestParam String password){

        User user=userService.login(name,password);
        return user;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        User result =userService.register(user);
        return result!=null?"success":"fail";
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable String id){
        System.out.println("=============this is form user provider===================");
        //return userService.getUserById(id);

        User user=new User();
        user.setId(1L);
        user.setName("hello");
        System.out.println("============("+user.toString()+"======================");
        return user;
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id,@ModelAttribute User user){

        User updatedUser =userService.getUserById(id);
        updatedUser.setName(user.getName());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setCreateDate(new Date());
        User result= userService.register(updatedUser);
        return result!=null?"success":"fail";

    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        User user =new User();
        user.setId(id);
        userService.writeOff(user);

        return "success";
    }


}

