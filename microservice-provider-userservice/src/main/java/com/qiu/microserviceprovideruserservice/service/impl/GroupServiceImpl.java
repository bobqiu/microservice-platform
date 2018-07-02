package com.qiu.microserviceprovideruserservice.service.impl;

import com.qiu.common.service.impl.BaseServiceImpl;
import com.qiu.microserviceprovideruserservice.dao.GroupRepository;
import com.qiu.microserviceprovideruserservice.entity.Group;
import com.qiu.microserviceprovideruserservice.service.GroupService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-07
 **/
@Service
public class GroupServiceImpl  extends BaseServiceImpl<GroupRepository,Group>implements GroupService {

    @Autowired
    GroupRepository groupRepository;
  /*  @Override
    public List<Group> findAll() {
        return groupRepository.findAll();
    }*/

    @Override
    public List<Group> findByNameOrType(String name, String groupType) {
        List<Group> groups = groupRepository.findAll(new Specification<Group>() {
            @Override
            public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                list.add(criteriaBuilder.equal(root.get("groupType").as(Integer.class), groupType));
                if (Strings.isNotEmpty(name)) {
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
                }
                Predicate[] p = new Predicate[list.size()];

                return criteriaBuilder.and(list.toArray(p));
            }
        });
        return groups;
    }
}
