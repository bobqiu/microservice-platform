package com.qiu.security.server.feign;

import com.qiu.security.server.config.FeignConfig;
import com.qiu.security.server.utils.jwt.JwtAuthenticationRequest;
import com.qiu.security.server.vo.user.User;
import com.qiu.security.server.vo.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-01
 **/
@FeignClient(value = "microservice-provider-userservice",configuration = FeignConfig.class)
public interface IUserService {
    @RequestMapping(value = "/api/v1/user/validate", method = RequestMethod.POST)
    public UserInfo validate(@RequestBody JwtAuthenticationRequest authenticationRequest);

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    List<User> list();
}
