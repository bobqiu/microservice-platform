package com.qiu.microserviceprovideruserservice.service;

import com.qiu.microserviceprovideruserservice.entity.Department;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
public interface DepartmentService {
    Department saveDepartment(Department department);

    Department getDepartmentById(Long id);
}
