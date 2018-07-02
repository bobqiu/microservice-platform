package com.qiu.security.server.controller;

import com.qiu.security.server.feign.IUserService;
import com.qiu.security.server.vo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-15
 **/
@RestController
public class UserFeignController {

    @Autowired
    private IUserService userService;
    @GetMapping(value = "/testuser/list")
    public List<User> list(){
        return userService.list();
    }
}
