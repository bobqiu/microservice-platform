package com.qiu.microserviceprovideruserservice.dao;

import com.qiu.microserviceprovideruserservice.entity.GroupType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-05
 **/
public interface GroupTypeRepository extends JpaRepository<GroupType,Long> {
}
