package com.qiu.microserviceconsumerh5ribbon.controller;

import com.qiu.microserviceconsumerh5ribbon.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-19
 **/
@RestController
public class UserController {



    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier(value = "lbcRestTemplate")
    private RestTemplate lbcRestTemplate;

   /* @Autowired
    private Registration registration;       // 服务注册*/
    @Autowired
    private DiscoveryClient discoveryClient; // 服务发现客户端

    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @GetMapping("/login")
    public User login(@RequestParam String name, @RequestParam String password){

        LOGGER.info("call user service login method");
        ResponseEntity<User> responseEntity =restTemplate.getForEntity("http://microservice-provider-userservice/login?name={1},password={2}",User.class,name,password);

        return responseEntity.getBody();
    }



    @GetMapping("/list")
    public List<User> list(){
        User[] users=restTemplate.getForObject("http://microservice-provider-userservice/list",User[].class);
        List<User> userList = Arrays.asList(users);
        return userList;

    }




    @GetMapping("user/get/{id}")
    public User get(@PathVariable Long id){
        ServiceInstance serviceInstance=this.loadBalancerClient.choose("microservice-provider-userservice");
        LOGGER.info("serviceInstance info ---> serviceId is  " + serviceInstance.getServiceId() + " host is " + serviceInstance.getHost() + "　port is " + serviceInstance.getPort());
        return lbcRestTemplate.getForObject("http://MICROSERVICE-PROVIDER-USERSERVICE/get/id={1}",User.class,id);
    }


    /**
     * ribbon负载均衡测试方法
     */
    @GetMapping("/log-user-service-instance")
    public void logUserServiceInstance(){

        ServiceInstance serviceInstance=this.loadBalancerClient.choose("microservice-provider-userservice");
        LOGGER.info("serviceInstance info ---> serviceId is  "+serviceInstance.getServiceId()+" host is "+serviceInstance.getHost()+"　port is "+serviceInstance.getPort() );
        LOGGER.info("serviceInstance info ---> serviceId is  "+serviceInstance.getServiceId()+" host is "+serviceInstance.getHost()+"port is "+serviceInstance.getPort() );
    }
    @GetMapping("/services")
    public ServiceInstance serviceInstance() {
        List<String> list = discoveryClient.getServices();
        if (list != null && list.size() > 0) {
            System.out.println("===============" + list.get(0) + "======================");
        } else {
            System.out.println("===============no==================");
        }

        return null;
    }
}