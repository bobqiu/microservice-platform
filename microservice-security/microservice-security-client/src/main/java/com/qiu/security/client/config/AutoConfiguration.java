package com.qiu.security.client.config;

import org.springframework.cloud.bus.jackson.RemoteApplicationEventScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-01
 **/
@Configuration
@ComponentScan({"com.qiu.security.client","com.qiu.security.common.event"})
@RemoteApplicationEventScan(basePackages = "com.qiu.security.common.event")
public class AutoConfiguration {
    @Bean
    ServiceAuthConfig getServiceAuthConfig(){
        return new ServiceAuthConfig();
    }

    @Bean
    UserAuthConfig getUserAuthConfig(){
        return new UserAuthConfig();
    }

    @Bean
    RequestMappingHandlerMapping  getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();}

}
