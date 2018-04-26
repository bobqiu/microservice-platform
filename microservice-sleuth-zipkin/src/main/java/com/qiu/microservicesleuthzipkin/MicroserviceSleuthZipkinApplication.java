package com.qiu.microservicesleuthzipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin.server.EnableZipkinServer;

/**
 * 访问http://127.0.0.1:8503/zipkin/
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZipkinServer
public class MicroserviceSleuthZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceSleuthZipkinApplication.class, args);
    }
}
