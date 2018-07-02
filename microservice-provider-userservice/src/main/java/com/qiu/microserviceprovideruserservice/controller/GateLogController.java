package com.qiu.microserviceprovideruserservice.controller;

import com.qiu.common.controller.BaseController;
import com.qiu.common.response.TableResultResponse;
import com.qiu.microserviceprovideruserservice.entity.GateLog;
import com.qiu.microserviceprovideruserservice.service.GateLogService;
import com.qiu.microserviceprovideruserservice.service.impl.GateLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-05
 **/
@RestController
@RequestMapping("gateLog")
public class GateLogController extends BaseController<GateLogServiceImpl,GateLog> {
    @Autowired
    GateLogService gateLogService;

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<GateLog> list(@RequestParam Map<String, Object> params){
        //查询列表数据

        return gateLogService.selectByQuery(params);
    }
}
