package com.qiu.common.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-15
 **/
public interface BaseService<res extends JpaRepository<T,Long>,T> {

    List<T> findAll();

    T selectById(Long id);

    void insertSelective(T entity);

    void updateSelectiveById(T entity);

    void deleteById(Long id);
}
