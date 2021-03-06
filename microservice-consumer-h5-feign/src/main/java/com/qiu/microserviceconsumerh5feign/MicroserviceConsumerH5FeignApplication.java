package com.qiu.microserviceconsumerh5feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * http://127.0.0.1:9993/list
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients//开启SpringCloud Feign的支持功能
@EnableAutoConfiguration
@EnableCircuitBreaker
//@EnableTurbine
public class MicroserviceConsumerH5FeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerH5FeignApplication.class, args);
    }
}
