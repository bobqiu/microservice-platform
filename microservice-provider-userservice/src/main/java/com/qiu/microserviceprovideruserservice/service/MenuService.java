package com.qiu.microserviceprovideruserservice.service;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-04
 **/
public interface MenuService<Menu> {
    com.qiu.microserviceprovideruserservice.entity.Menu selectById(long id);

    List<Menu> findByTitleLike(String title);

    List<Menu> findAll();
}
