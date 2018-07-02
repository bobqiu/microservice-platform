package com.qiu.microserviceprovideruserservice.controller;

import com.qiu.common.controller.BaseController;
import com.qiu.common.response.TableResultResponse;
import com.qiu.microserviceprovideruserservice.biz.GroupTypeBiz;
import com.qiu.microserviceprovideruserservice.entity.GroupType;
import com.qiu.microserviceprovideruserservice.service.GroupTypeService;
import com.qiu.microserviceprovideruserservice.service.impl.GroupTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-05
 **/
@RestController
@RequestMapping("/groupType")
//public class GroupTypeController extends BaseController<GroupTypeBiz,GroupType>{
public class GroupTypeController extends BaseController<GroupTypeServiceImpl,GroupType>{

    @Autowired
    GroupTypeService groupTypeService;

    /*@RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<GroupType> all(){
        return groupTypeService.selectListAll();
    }*/
    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<GroupType> list(@RequestParam Map<String, Object> params){
        //查询列表数据

        return groupTypeService.selectByQuery(params);
    }
}
