package com.qiu.microserviceprovideruserservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceProviderUserserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceProviderUserserviceApplication.class, args);
    }
}
