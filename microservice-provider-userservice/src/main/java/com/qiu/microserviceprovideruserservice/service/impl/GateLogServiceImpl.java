package com.qiu.microserviceprovideruserservice.service.impl;

import com.qiu.common.response.TableResultResponse;
import com.qiu.common.service.impl.BaseServiceImpl;
import com.qiu.microserviceprovideruserservice.dao.GateLogRepository;
import com.qiu.microserviceprovideruserservice.entity.GateLog;
import com.qiu.microserviceprovideruserservice.service.GateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-05
 **/
@Service
@Slf4j
public class GateLogServiceImpl  extends BaseServiceImpl<GateLogRepository,GateLog> implements GateLogService {

    @Autowired
    GateLogRepository gateLogRepository;
    @Override
    public TableResultResponse<GateLog> selectByQuery(Map<String, Object> params) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        int page = Integer.parseInt((String) params.get("page"))-1 ;
        int limit = Integer.parseInt(String.valueOf(params.get("limit")));
        Pageable pageable = PageRequest.of(page, limit, sort);
        log.debug("===============page:{},limit:{}",page,limit);
        Page<GateLog> gateLogs = gateLogRepository.findAll(pageable);
        return new TableResultResponse<>(gateLogs.getTotalPages(), gateLogs.getContent());
    }
}
