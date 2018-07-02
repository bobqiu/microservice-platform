package com.qiu.microserviceprovideruserservice.controller;

import com.qiu.common.controller.BaseController;
import com.qiu.common.response.TableResultResponse;
import com.qiu.microserviceprovideruserservice.entity.Menu;
import com.qiu.microserviceprovideruserservice.entity.User;
import com.qiu.microserviceprovideruserservice.service.PermissionService;
import com.qiu.microserviceprovideruserservice.service.UserService;
import com.qiu.microserviceprovideruserservice.service.impl.UserServiceImpl;
import com.qiu.microserviceprovideruserservice.vo.FrontUser;
import com.qiu.microserviceprovideruserservice.vo.MenuTree;
import com.qiu.security.client.config.UserAuthConfig;
import com.qiu.security.common.context.BaseContextHandler;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
@RestController
@RequestMapping("/user")
public class UserController extends BaseController<UserServiceImpl,User> {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    UserAuthConfig userAuthConfig;

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @RequestMapping(value = "/front/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) throws Exception {
        String token = userAuthConfig.getToken(request);
        LOGGER.debug("===========token:{}", token);
        FrontUser userInfo = permissionService.getUserInfo(token);
        LOGGER.debug("token:{},userInfo:{}", token, userInfo.toString());
        if(userInfo==null) {
            return ResponseEntity.status(401).body(false);
        } else {
            return ResponseEntity.ok(userInfo);
        }
    }

    @RequestMapping(value = "/front/menus", method = RequestMethod.GET)
    public @ResponseBody
    List<MenuTree> getMenusByUsername(HttpServletRequest request) throws Exception {
        String token = userAuthConfig.getToken(request);
        LOGGER.debug("===========token:{}", token);
        return permissionService.getMenusByUsername(token);
    }

    @RequestMapping(value = "/front/menu/all", method = RequestMethod.GET)
    public @ResponseBody
    List<Menu> getAllMenus() throws Exception {
        return permissionService.selectListAll();
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<User> list(@RequestParam Map<String, Object> params){
        //查询列表数据
       // Query query = new Query(params);
        //return baseBiz.selectByQuery(query);
        return userService.getUserTableResult(params);
    }

    @GetMapping(value = "/list")
    public List<User> list(){

       // ServiceInstance instance=discoveryClient.getLocalServiceInstance();
      //  LOGGER.info("call user/list service  host is  "+instance.getHost()+"service_id is "+instance.getServiceId());
        return userService.getAllUser();
    }

    @GetMapping(value = "/login")
    public User login(@RequestParam String name, @RequestParam String password){

        User user=userService.login(name,password);
        return user;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        User result =userService.register(user);
        return result!=null?"success":"fail";
    }

    @GetMapping("/get/{id}")
    public User get(@PathVariable String id){
        System.out.println("=============this is form user provider===================");
        //return userService.getUserById(id);

        User user=new User();
        user.setId(1L);
        user.setName("hello");
        System.out.println("============("+user.toString()+"======================");
        return user;
    }

    @PutMapping("/update/{id}")
    public String update(@PathVariable Long id,@ModelAttribute User user){

        User updatedUser =userService.getUserById(id);
        updatedUser.setName(user.getName());
        updatedUser.setPassWord(user.getPassWord());
        updatedUser.setCrtTime(new Date());
        User result= userService.register(updatedUser);
        return result!=null?"success":"fail";

    }


    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        User user =new User();
        user.setId(id);
        userService.writeOff(user);

        return "success";
    }



}

