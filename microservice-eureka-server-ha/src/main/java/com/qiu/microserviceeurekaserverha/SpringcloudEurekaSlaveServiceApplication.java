package com.qiu.microserviceeurekaserverha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
@EnableEurekaServer
@SpringBootApplication
public class SpringcloudEurekaSlaveServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudEurekaSlaveServiceApplication.class, args);
    }
}