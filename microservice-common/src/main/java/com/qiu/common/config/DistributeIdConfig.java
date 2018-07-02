package com.qiu.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-27
 **/
@Order(Ordered.HIGHEST_PRECEDENCE)
@Data
@Component
@Configuration
@ComponentScan(basePackages = "com.qiu.common")
public class DistributeIdConfig {

  /*  @Value("${distributeid.machine-id}")
    private String machineId;

    @Value("${distributeid.data-center-id}")
    private String dataCenterId;*/
}
