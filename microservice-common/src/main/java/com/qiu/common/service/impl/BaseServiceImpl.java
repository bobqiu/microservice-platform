package com.qiu.common.service.impl;

import com.qiu.common.service.BaseService;
import com.qiu.common.utils.EntityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-15
 **/
@Slf4j
public class BaseServiceImpl<res extends JpaRepository<T, Long>, T> implements BaseService<res, T> {

    @Autowired
    protected res repository;

  /*  public void setRepository(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }*/


    @Override
    public List<T> findAll() {
        log.debug("&&&&&&&&&&&&&&&&&&& this is come from BaseServiceImpl");
        return repository.findAll();
    }

    @Override
    public T selectById(Long id) {
        log.debug("&&&&&&&&&&&&&&&&&&& this is come from BaseServiceImpl");
        return (T) repository.findById(id);

    }

    @Override
    public void insertSelective(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        repository.save(entity);
    }

    @Override
    public void updateSelectiveById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
