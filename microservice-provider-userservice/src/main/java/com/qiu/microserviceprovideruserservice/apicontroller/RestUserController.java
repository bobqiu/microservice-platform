package com.qiu.microserviceprovideruserservice.apicontroller;

import com.qiu.common.api.vo.UserInfo;
import com.qiu.common.vo.PermissionInfo;
import com.qiu.microserviceprovideruserservice.entity.User;
import com.qiu.microserviceprovideruserservice.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-17
 **/
@RestController
@RequestMapping("api/v1")
public class RestUserController {
    @Autowired
    private PermissionService permissionService;


        //@Cache(key="permission")
        @RequestMapping(value = "/permissions", method = RequestMethod.GET)
        public @ResponseBody
        List<PermissionInfo> getAllPermission(){
            return permissionService.getAllPermission();
        }

       // @Cache(key="permission:u{1}")
        @RequestMapping(value = "/user/un/{username}/permissions", method = RequestMethod.GET)
        public @ResponseBody List<PermissionInfo> getPermissionByUsername(@PathVariable("username") String username){
            return permissionService.getPermissionByUsername(username);
        }

   @RequestMapping(value = "/user/validate", method = RequestMethod.POST)
   @ResponseBody
   public UserInfo validate(@RequestBody Map<String,String> body){
       UserInfo user = permissionService.validate(body.get("username"), body.get("password"));
       return user;
   }
}
