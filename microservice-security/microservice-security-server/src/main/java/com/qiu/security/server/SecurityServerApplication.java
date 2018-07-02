package com.qiu.security.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
//@RemoteApplicationEventScan(basePackages = "com.qiu.security.common.event")
@EnableAutoConfiguration
@EnableScheduling
public class SecurityServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityServerApplication.class, args);
    }
}
