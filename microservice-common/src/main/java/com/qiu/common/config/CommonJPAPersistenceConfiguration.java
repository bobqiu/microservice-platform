package com.qiu.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-19
 **/
/*@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
@EnableJpaRepositories(basePackages = {"com.qiu.common.dao"})//启用JPA资源库并指定资源库接口位置
//@EntityScan(basePackages = {"com.qiu.common.entity"})//指定实体的位置*/
public class CommonJPAPersistenceConfiguration {

}