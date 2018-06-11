package com.qiu.microservicezuul;

import com.qiu.security.client.EnableAuthClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 访问：http://127.0.0.1:8501/microservice-consumer-h5-feign/user/get/1
 * 　　or http://127.0.0.1:8501/consumer/user/get/1
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@RefreshScope
@EnableScheduling
@EnableFeignClients({"com.qiu.security.client.feign","com.qiu.microservicezuul.feign"})
@EnableAuthClient
@ComponentScan(basePackages = {"com.qiu.security.client","com.qiu.security.common","com.qiu.common","com.qiu.microservicezuul"})
public class MicroserviceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceZuulApplication.class, args);
    }
}
