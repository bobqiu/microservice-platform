package com.qiu.common.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-19
 **/
public abstract class BaseBiz<res extends JpaRepository<T,Long>,T> {

    @Autowired
    protected res repository;
    
    public List<T> findAll() {
        return repository.findAll();
    }
}
