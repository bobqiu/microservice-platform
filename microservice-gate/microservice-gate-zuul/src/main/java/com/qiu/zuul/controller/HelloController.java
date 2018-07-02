package com.qiu.zuul.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-29
 **/
@RestController
public class HelloController {
    @RequestMapping("/local")
    public String hello() {
        return "hello api gateway";
    }
}