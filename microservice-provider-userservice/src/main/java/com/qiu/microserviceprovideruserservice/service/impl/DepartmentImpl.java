package com.qiu.microserviceprovideruserservice.service.impl;

import com.qiu.microserviceprovideruserservice.dao.DepartmentRepository;
import com.qiu.microserviceprovideruserservice.entity.Department;
import com.qiu.microserviceprovideruserservice.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
@Transactional
@Service
public class DepartmentImpl implements DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;


    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findByIdAndName(id,"");
    }
}

