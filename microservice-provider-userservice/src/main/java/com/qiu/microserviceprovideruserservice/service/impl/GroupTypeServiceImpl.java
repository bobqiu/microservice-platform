package com.qiu.microserviceprovideruserservice.service.impl;

import com.netflix.discovery.converters.Auto;
import com.qiu.common.biz.BaseBiz;
import com.qiu.common.response.TableResultResponse;
import com.qiu.common.service.BaseService;
import com.qiu.common.service.impl.BaseServiceImpl;
import com.qiu.microserviceprovideruserservice.dao.GroupTypeRepository;
import com.qiu.microserviceprovideruserservice.entity.GroupType;
import com.qiu.microserviceprovideruserservice.entity.User;
import com.qiu.microserviceprovideruserservice.service.GroupTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-05
 **/
@Service
@Slf4j
public class GroupTypeServiceImpl extends BaseServiceImpl<GroupTypeRepository,GroupType> implements GroupTypeService {

    @Autowired
    GroupTypeRepository groupTypeRepository;

    @Override
    public List<GroupType> selectListAll() {
        return groupTypeRepository.findAll();
    }

    @Override
    public TableResultResponse<GroupType> selectByQuery(Map<String, Object> params) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        int page = Integer.parseInt((String) params.get("page"))-1 ;
        int limit = Integer.parseInt(String.valueOf(params.get("limit")));
        Pageable pageable = PageRequest.of(page, limit, sort);
        log.debug("===============page:{},limit:{}",page,limit);
        Page<GroupType> groupTypes = groupTypeRepository.findAll(pageable);
        return new TableResultResponse<>(groupTypes.getTotalPages(), groupTypes.getContent());
    }
}
