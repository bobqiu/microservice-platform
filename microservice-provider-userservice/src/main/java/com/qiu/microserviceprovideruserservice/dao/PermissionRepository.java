package com.qiu.microserviceprovideruserservice.dao;

import com.qiu.microserviceprovideruserservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-19
 **/
@Repository
public interface PermissionRepository extends JpaRepository<User, Long> {

}
