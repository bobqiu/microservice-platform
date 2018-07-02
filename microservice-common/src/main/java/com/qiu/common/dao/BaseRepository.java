package com.qiu.common.dao;

import com.qiu.common.entity.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-18
 **/
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T,Long>,JpaSpecificationExecutor<T> {
    List<T> findAll(Specification<T> specification);

    Page<T> findAll(Specification<T> specification, Pageable pageable);

    Optional<T> findById(Long id);
}
