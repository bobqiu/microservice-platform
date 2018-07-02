package com.qiu.security.server.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
@Configuration
@Data
public class UserConfig {
    @Value("${jwt.token-header}")
    private String userTokenHeader;
}
