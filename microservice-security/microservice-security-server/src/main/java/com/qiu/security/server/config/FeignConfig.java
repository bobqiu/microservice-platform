package com.qiu.security.server.config;

import com.qiu.security.server.interceptor.ClientTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
@Configuration
public class FeignConfig {
    @Bean
    ClientTokenInterceptor getClientTokenInterceptor(){
        return new ClientTokenInterceptor();
    }
}