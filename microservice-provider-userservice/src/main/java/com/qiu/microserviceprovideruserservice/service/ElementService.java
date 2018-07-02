package com.qiu.microserviceprovideruserservice.service;

import com.qiu.common.response.TableResultResponse;
import com.qiu.microserviceprovideruserservice.entity.Element;

import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-04
 **/
public interface ElementService {
    TableResultResponse<Element> selectElementList(Map<String, String> params);
}
