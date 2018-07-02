package com.qiu.microserviceprovideruserservice.controller;

import com.qiu.common.controller.BaseController;
import com.qiu.common.response.TableResultResponse;
import com.qiu.microserviceprovideruserservice.entity.Element;
import com.qiu.microserviceprovideruserservice.service.ElementService;
import com.qiu.microserviceprovideruserservice.service.impl.ElementServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-04
 **/
@RestController
@RequestMapping("/element")
@Slf4j
public class ElementController extends BaseController<ElementServiceImpl,Element> {


    @Autowired
    ElementService elementService;
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Element> page(@RequestParam Map<String, String> params) {

       /* Example example = new Example(Element.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("menuId", menuId);
        if(StringUtils.isNotBlank(name)){
            criteria.andLike("name", "%" + name + "%");
        }*/
        //List<Element> elements = baseBiz.selectByExample(example);

        return elementService.selectElementList(params);
    }
}
