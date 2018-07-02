package com.qiu.security.server.controller;

import com.qiu.security.common.response.ObjectRestResponse;
import com.qiu.security.server.model.Client;
import com.qiu.security.server.model.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-16
 **/
@RestController
@RequestMapping("service")
public class ServiceController{// extends BaseController<ClientBiz,Client>{

    @RequestMapping(value = "/{id}/client", method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse modifyUsers(@PathVariable int id, String clients){
       // baseBiz.modifyClientServices(id, clients);
        return new ObjectRestResponse().rel(true);
    }

    @RequestMapping(value = "/{id}/client", method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse<ClientService> getUsers(@PathVariable int id){
        List<Client> list = new ArrayList<>();
        //list=baseBiz.getClientServices(id)
        return new ObjectRestResponse<ClientService>().rel(true).data(list);
    }
}
