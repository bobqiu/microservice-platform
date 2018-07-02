package com.qiu.microserviceprovideruserservice;

import com.qiu.security.client.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
//@EnableTurbine
//@EnableFeignClients(basePackages = {"com.qiu.security.client"})
@EnableScheduling
@EnableFeignClients({"com.qiu.security.client.feign"})
@EnableAuthClient
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.qiu.security.client","com.qiu.microserviceprovideruserservice","com.qiu.common"})
public class MicroserviceProviderUserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceProviderUserserviceApplication.class, args);
    }
}
