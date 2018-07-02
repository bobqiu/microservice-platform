package com.qiu.microserviceprovideruserservice.service.impl;

import com.qiu.common.response.TableResultResponse;
import com.qiu.common.service.impl.BaseServiceImpl;
import com.qiu.microserviceprovideruserservice.dao.UserRepository;
import com.qiu.microserviceprovideruserservice.entity.User;
import com.qiu.microserviceprovideruserservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
@Service
@Transactional
@Slf4j
public class UserServiceImpl  extends BaseServiceImpl<UserRepository,User> implements UserService {



    @Autowired
    private UserRepository userRepository;


    @Override
    public User login(String name, String password) {
        return userRepository.findByUserName(name);
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public void writeOff(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean isExists(User user) {
        return userRepository.findByIdOrName(user.getId(),user.getName())!=null?true:false;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findByIdOrName(id,"");
    }

    @Override
    public TableResultResponse<User> getUserTableResult(Map<String, Object> params) {

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        int page = Integer.parseInt((String) params.get("page"))-1 ;
        int limit = Integer.parseInt(String.valueOf(params.get("limit")));
        Pageable pageable = PageRequest.of(page, limit, sort);
        log.debug("===============page:{},limit:{}",page,limit);
        Page<User> users = userRepository.findAll(pageable);
        TableResultResponse<User> userTableResultResponse = new TableResultResponse<>(users.getTotalPages(), users.getContent());
        log.debug("------------{}",userTableResultResponse.getData());
        return userTableResultResponse;
    }
}
