package com.qiu.microserviceprovideruserservice.service;

import com.qiu.microserviceprovideruserservice.entity.Group;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-07
 **/
public interface GroupService {
  /*  List<Group> findAll();*/

    List<Group> findByNameOrType(String name, String groupType);
}
