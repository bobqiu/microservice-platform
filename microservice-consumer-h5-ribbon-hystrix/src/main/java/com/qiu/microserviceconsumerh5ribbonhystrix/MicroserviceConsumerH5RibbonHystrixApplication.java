package com.qiu.microserviceconsumerh5ribbonhystrix;

import com.qiu.microserviceconsumerh5ribbonhystrix.conf.MVCConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
