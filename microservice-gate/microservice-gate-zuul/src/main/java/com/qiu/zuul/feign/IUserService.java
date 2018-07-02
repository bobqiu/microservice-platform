package com.qiu.zuul.feign;

import com.qiu.common.vo.PermissionInfo;
import com.qiu.zuul.fallback.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-06
 **/
@FeignClient(value = "microservice-provider-userservice",fallback = UserServiceFallback.class)
public interface IUserService {
    @RequestMapping(value="/api/v1/user/un/{username}/permissions",method = RequestMethod.GET)
    public List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username);
    @RequestMapping(value="/api/v1/permissions",method = RequestMethod.GET)
    List<PermissionInfo> getAllPermissionInfo();
}

