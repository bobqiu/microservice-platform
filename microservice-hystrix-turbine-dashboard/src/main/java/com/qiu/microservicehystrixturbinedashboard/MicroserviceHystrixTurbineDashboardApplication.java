package com.qiu.microservicehystrixturbinedashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * 访问　http://127.0.0.1:8502/hystrix
 * http://127.0.0.1:8502/turbine.stream
 * http://192.168.1.10:9993/actuator/hystrix.stream
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableHystrixDashboard
@EnableTurbine
public class MicroserviceHystrixTurbineDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceHystrixTurbineDashboardApplication.class, args);
    }
}
