package com.qiu.microserviceprovideruserservice.controller;

import com.qiu.common.controller.BaseController;
import com.qiu.common.utils.TreeUtil;
import com.qiu.microserviceprovideruserservice.constant.AdminCommonConstant;
import com.qiu.microserviceprovideruserservice.entity.Menu;
import com.qiu.microserviceprovideruserservice.service.MenuService;
import com.qiu.microserviceprovideruserservice.service.impl.MenuServiceImpl;
import com.qiu.microserviceprovideruserservice.vo.MenuTree;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-04
 **/
@RestController
@RequestMapping("/menu")
@Slf4j
public class MenuController  extends BaseController<MenuServiceImpl,Menu>{

    @Autowired
    MenuService menuService;

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuTree> getTree(String title) {
        List<Menu> menus = new ArrayList<>();
        if (StringUtils.isNotBlank(title)) {
            // example.createCriteria().andLike("title", "%" + title + "%");
            menus = menuService.findByTitleLike(title);
        } else {
            menus = menuService.findAll();
        }
        return getMenuTree(menus, AdminCommonConstant.ROOT);
    }

    private List<MenuTree> getMenuTree(List<Menu> menus,long root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            node.setLabel(menu.getTitle());
            trees.add(node);
        }
        return TreeUtil.bulid(trees,root) ;
    }

}