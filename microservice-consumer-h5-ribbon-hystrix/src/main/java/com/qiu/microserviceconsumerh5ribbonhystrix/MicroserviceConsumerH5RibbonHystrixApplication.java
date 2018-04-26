package com.qiu.microserviceconsumerh5ribbonhystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableCircuitBreaker //启动断路器支持
@EnableDiscoveryClient
//@EnableEurekaClient
@SpringBootApplication
public class MicroserviceConsumerH5RibbonHystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerH5RibbonHystrixApplication.class, args);
    }


  /*  @LoadBalanced
    @Bean(name="lbcRestTemplate")
    RestTemplate lbcRestTemplate() {
        return new RestTemplate();
    }*/
}
