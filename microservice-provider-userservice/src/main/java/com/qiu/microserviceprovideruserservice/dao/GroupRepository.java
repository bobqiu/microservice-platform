package com.qiu.microserviceprovideruserservice.dao;

import com.qiu.microserviceprovideruserservice.entity.Group;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-07
 **/
@Repository
public interface GroupRepository extends JpaRepository<Group,Long> {
    List<Group> findAll(Specification<Group> specification);
}
