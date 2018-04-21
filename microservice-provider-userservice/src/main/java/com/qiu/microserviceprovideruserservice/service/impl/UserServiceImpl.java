package com.qiu.microserviceprovideruserservice.service.impl;

import com.qiu.microserviceprovideruserservice.dao.UserRepository;
import com.qiu.microserviceprovideruserservice.entity.User;
import com.qiu.microserviceprovideruserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
@Service
@Transactional
public class UserServiceImpl implements UserService {



    @Autowired
    private UserRepository userRepository;


    @Override
    public User login(String name, String password) {
        return userRepository.findByNameAndPassword(name,password);
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
}
