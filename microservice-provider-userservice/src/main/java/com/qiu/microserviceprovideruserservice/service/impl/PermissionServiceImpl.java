package com.qiu.microserviceprovideruserservice.service.impl;

import com.qiu.common.api.vo.UserInfo;
import com.qiu.common.constant.UserConstant;
import com.qiu.common.utils.TreeUtil;
import com.qiu.common.vo.PermissionInfo;
import com.qiu.microserviceprovideruserservice.constant.AdminCommonConstant;
import com.qiu.microserviceprovideruserservice.dao.ElementRepository;
import com.qiu.microserviceprovideruserservice.dao.MenuRepository;
import com.qiu.microserviceprovideruserservice.dao.UserRepository;
import com.qiu.microserviceprovideruserservice.entity.Element;
import com.qiu.microserviceprovideruserservice.entity.Menu;
import com.qiu.microserviceprovideruserservice.entity.User;
import com.qiu.microserviceprovideruserservice.service.PermissionService;
import com.qiu.microserviceprovideruserservice.vo.FrontUser;
import com.qiu.microserviceprovideruserservice.vo.MenuTree;
import com.qiu.security.client.utils.UserAuthUtil;
import com.qiu.security.common.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-19
 **/
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    ElementRepository elementRepository;

    @Autowired
    private UserAuthUtil userAuthUtil;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT);
    @Override
    public UserInfo validate(String username, String password) {
        UserInfo info = new UserInfo();
        User user = userRepository.findByUserName(username);
        if (encoder.matches(password, user.getPassWord())) {
            BeanUtils.copyProperties(user, info);
            info.setId(String.valueOf(user.getId()));
        }
        return info;
    }

    @Override
    public FrontUser getUserInfo(String token) throws Exception {
        String username = userAuthUtil.getInfoFromToken(token).getUniqueName();
        log.debug("============= getUserInfo username:{}",username);
        if (username == null) {
            return null;
        }
        UserInfo user = this.getUserByUsername(username);
        FrontUser frontUser = new FrontUser();
        BeanUtils.copyProperties(user, frontUser);
        List<PermissionInfo> permissionInfos = this.getPermissionByUsername(username);
        Stream<PermissionInfo> menus = permissionInfos.parallelStream().filter((permission) -> {
            return permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setMenus(menus.collect(Collectors.toList()));
        Stream<PermissionInfo> elements = permissionInfos.parallelStream().filter((permission) -> {
            return !permission.getType().equals(CommonConstants.RESOURCE_TYPE_MENU);
        });
        frontUser.setElements(elements.collect(Collectors.toList()));
        return frontUser;
    }

    @Override
    public List<MenuTree> getMenusByUsername(String token) throws Exception {
        String username = userAuthUtil.getInfoFromToken(token).getUniqueName();
        log.debug("=============getMenusByUsername username:{}",username);
        if (username == null) {
            return null;
        }
        User user = userRepository.getByUserName(username);
        List<Menu> menus = menuRepository.getUserAuthorityMenuByUserId(user.getId());
        List<MenuTree> menuTree = getMenuTree(menus, AdminCommonConstant.ROOT);
        return menuTree;
    }

    @Override
    public List<Menu> selectListAll() {
        return menuRepository.findAll();
    }

    @Override
    public List<PermissionInfo> getAllPermission() {
        List<Menu> menus = menuRepository.findAll();
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Element> elements = elementRepository.getAllElementPermissions();
        element2permission(result, elements);
        return result;
    }

    public UserInfo getUserByUsername(String username) {
        UserInfo info = new UserInfo();
        User user =  userRepository.getByUserName(username);
        BeanUtils.copyProperties(user, info);
        info.setId(String.valueOf(user.getId()));
        return info;
    }

    public List<PermissionInfo> getPermissionByUsername(String username) {
        User user = userRepository.getByUserName(username);
        List<Menu> menus = menuRepository.getUserAuthorityMenuByUserId(user.getId());
        List<PermissionInfo> result = new ArrayList<PermissionInfo>();
        PermissionInfo info = null;
        menu2permission(menus, result);
        List<Element> elements = elementRepository.getAuthorityElementByUserId(user.getId(),user.getId());
        element2permission(result, elements);
        return result;
    }

    private void menu2permission(List<Menu> menus, List<PermissionInfo> result) {
        PermissionInfo info;
        for (Menu menu : menus) {
            if (StringUtils.isBlank(menu.getHref())) {
                menu.setHref("/" + menu.getCode());
            }
            info = new PermissionInfo();
            info.setCode(menu.getCode());
            info.setType(AdminCommonConstant.RESOURCE_TYPE_MENU);
            info.setName(AdminCommonConstant.RESOURCE_ACTION_VISIT);
            String uri = menu.getHref();
            if (!uri.startsWith("/")) {
                uri = "/" + uri;
            }
            info.setUri(uri);
            info.setMethod(AdminCommonConstant.RESOURCE_REQUEST_METHOD_GET);
            result.add(info
            );
            info.setMenu(menu.getTitle());
        }
    }

    private void element2permission(List<PermissionInfo> result, List<Element> elements) {
        PermissionInfo info;
        for (Element element : elements) {
            info = new PermissionInfo();
            info.setCode(element.getCode());
            info.setType(element.getType());
            info.setUri(element.getUri());
            info.setMethod(element.getMethod());
            info.setName(element.getName());
            info.setMenu(element.getMenuId());
            result.add(info);
        }
    }

    private List<MenuTree> getMenuTree(List<Menu> menus, long root) {
        List<MenuTree> trees = new ArrayList<MenuTree>();
        MenuTree node = null;
        for (Menu menu : menus) {
            node = new MenuTree();
            BeanUtils.copyProperties(menu, node);
            trees.add(node);
        }
        List<MenuTree> menuTrees = TreeUtil.bulid(trees, root);
        return menuTrees;
    }

    public static void main(String[] args) {
        String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode("admin");
        System.out.println("password:"+password);
    }
}
