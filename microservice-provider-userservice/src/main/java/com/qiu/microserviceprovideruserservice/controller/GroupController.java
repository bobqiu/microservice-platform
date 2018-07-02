package com.qiu.microserviceprovideruserservice.controller;

import com.qiu.common.controller.BaseController;
import com.qiu.common.utils.TreeUtil;
import com.qiu.microserviceprovideruserservice.constant.AdminCommonConstant;
import com.qiu.microserviceprovideruserservice.entity.Group;
import com.qiu.microserviceprovideruserservice.service.GroupService;
import com.qiu.microserviceprovideruserservice.service.impl.GroupServiceImpl;
import com.qiu.microserviceprovideruserservice.vo.GroupTree;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-07
 **/
@Controller
@RequestMapping("group")
public class GroupController extends BaseController<GroupServiceImpl,Group> {

    @Autowired
    private GroupService groupService;

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    public List<GroupTree> tree(String name, String groupType) {
        List<Group> menus = groupService.findByNameOrType(name,groupType);
        return getGroupTree(menus, AdminCommonConstant.ROOT);
    }

    private List<GroupTree> getGroupTree(List<Group> groups, long root) {
        List<GroupTree> trees = new ArrayList<GroupTree>();
        GroupTree node = null;
        for (Group group : groups) {
            node = new GroupTree();
            node.setLabel(group.getName());
            BeanUtils.copyProperties(group, node);
            trees.add(node);
        }
        return TreeUtil.bulid(trees,root) ;
    }
}
