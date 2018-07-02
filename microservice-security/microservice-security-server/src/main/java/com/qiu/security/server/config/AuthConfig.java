package com.qiu.security.server.config;

import com.qiu.security.common.exception.handler.GlobalExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
@Configuration
public class AuthConfig {
    @Bean
    public GlobalExceptionHandler getGlobalExceptionHandler(){
        return new GlobalExceptionHandler();
    }

}
