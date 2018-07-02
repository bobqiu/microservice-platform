package com.qiu.microserviceprovideruserservice.service;

import com.qiu.common.api.vo.UserInfo;
import com.qiu.common.vo.PermissionInfo;
import com.qiu.microserviceprovideruserservice.entity.Menu;
import com.qiu.microserviceprovideruserservice.entity.User;
import com.qiu.microserviceprovideruserservice.vo.FrontUser;
import com.qiu.microserviceprovideruserservice.vo.MenuTree;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-19
 **/

public interface PermissionService {
    UserInfo validate(String username, String password);

    FrontUser getUserInfo(String token) throws Exception;

    List<MenuTree> getMenusByUsername(String token) throws Exception;

    List<Menu> selectListAll();

    List<PermissionInfo> getAllPermission();

    List<PermissionInfo> getPermissionByUsername(String username);
}
