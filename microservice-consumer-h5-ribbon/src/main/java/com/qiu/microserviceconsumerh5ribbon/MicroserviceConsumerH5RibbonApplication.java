package com.qiu.microserviceconsumerh5ribbon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceConsumerH5RibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceConsumerH5RibbonApplication.class, args);
    }

   /* @Bean
    @LoadBalanced//开启客户端负载均衡
    public RestTemplate restTemplate(){

        return new RestTemplate();
    }*/
   @LoadBalanced
   @Bean(name="lbcRestTemplate")
   RestTemplate lbcRestTemplate() {
       return new RestTemplate();
   }
}
