package com.qiu.microserviceprovideruserservice.dao;

import com.qiu.microserviceprovideruserservice.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByIdAndName(Long id, String name);
}
