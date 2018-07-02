package com.qiu.microserviceprovideruserservice.service.impl;

import com.qiu.common.service.BaseService;
import com.qiu.common.service.impl.BaseServiceImpl;
import com.qiu.microserviceprovideruserservice.dao.MenuRepository;
import com.qiu.microserviceprovideruserservice.entity.Menu;
import com.qiu.microserviceprovideruserservice.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-04
 **/
@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuRepository,Menu> implements MenuService<Menu> {

    @Autowired
    MenuRepository menuRepository;

    @Override
    public Menu selectById(long id) {
        return menuRepository.findById(id);
    }

    @Override
    public List<Menu> findByTitleLike(String title) {
        return menuRepository.findByTitleLike(title);
    }

    @Override
    public List<Menu> findAll() {
        return menuRepository.findAll();
    }
}
