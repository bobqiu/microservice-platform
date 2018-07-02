package com.qiu.microserviceprovideruserservice.service;

import com.qiu.common.response.TableResultResponse;
import com.qiu.microserviceprovideruserservice.entity.GateLog;

import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-05
 **/
public interface GateLogService {
    TableResultResponse<GateLog> selectByQuery(Map<String, Object> params);
}
