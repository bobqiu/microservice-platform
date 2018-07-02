package com.qiu.microserviceprovideruserservice.service;

import com.qiu.common.biz.BaseBiz;
import com.qiu.common.response.TableResultResponse;
import com.qiu.microserviceprovideruserservice.entity.GroupType;

import java.util.List;
import java.util.Map;

/**
 * @description:
 *
 * @author: bobqiu
 * @create: 2018-06-05
 **/
public interface GroupTypeService {
    List<GroupType> selectListAll();

    TableResultResponse<GroupType> selectByQuery(Map<String,Object> params);
}
