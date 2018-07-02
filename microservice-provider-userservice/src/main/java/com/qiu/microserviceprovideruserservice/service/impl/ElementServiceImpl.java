package com.qiu.microserviceprovideruserservice.service.impl;

import com.google.common.base.Strings;
import com.qiu.common.response.TableResultResponse;
import com.qiu.common.service.impl.BaseServiceImpl;
import com.qiu.microserviceprovideruserservice.dao.ElementRepository;
import com.qiu.microserviceprovideruserservice.entity.Element;
import com.qiu.microserviceprovideruserservice.entity.Menu;
import com.qiu.microserviceprovideruserservice.service.ElementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-04
 **/

@Service
public class ElementServiceImpl  extends BaseServiceImpl<ElementRepository,Element> implements ElementService {

    @Autowired
    ElementRepository elementRepository;

    @Override
    public TableResultResponse<Element> selectElementList(Map<String, String> params) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        int page = Integer.parseInt((String) params.get("page"))-1 ;
        int limit = Integer.parseInt(String.valueOf(params.get("limit")));
        Pageable pageable = PageRequest.of(page, limit, sort);
        String menuId = String.valueOf(params.get("menuId"));
        Page<Element> elements = elementRepository.findAll(new Specification<Element>() {
            @Override
            public Predicate toPredicate(Root<Element> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                String name = params.get("name");
                if(StringUtils.isNotEmpty(name)){
                    list.add(criteriaBuilder.like(root.get("name").as(String.class), "%" + name + "%"));
                }
                list.add(criteriaBuilder.equal(root.get("menuId").as(String.class), menuId));
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return new TableResultResponse<>(elements.getTotalPages(), elements.getContent());
    }
}
