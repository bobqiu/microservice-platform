package com.qiu.zuul;

import com.qiu.ratelimiter.user.IUserPrincipal;
import com.qiu.security.client.EnableAuthClient;
import com.qiu.zuul.config.UserPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
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
//@EnableRateLimiter
@EnableFeignClients({"com.qiu.security.client.feign","com.qiu.zuul.feign"})
@EnableAuthClient
//@ComponentScan(basePackages = {"com.qiu"})
@ComponentScan(basePackages = {"com.qiu.security.client","com.qiu.ratelimiter","com.qiu.zuul","com.qiu.common"})
public class MicroserviceZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceZuulApplication.class, args);
    }

    @Bean
    @Primary
    IUserPrincipal userPrincipal(){
        return new UserPrincipal();
    }
}
