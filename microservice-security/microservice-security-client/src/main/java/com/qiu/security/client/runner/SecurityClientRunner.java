package com.qiu.security.client.runner;

import com.qiu.common.response.BaseResponse;
import com.qiu.security.client.config.ServiceAuthConfig;
import com.qiu.security.client.config.UserAuthConfig;
import com.qiu.security.client.feign.ServiceAuthFeign;
import com.qiu.security.common.response.ObjectRestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-02
 **/
@Configuration
@Slf4j
public class SecurityClientRunner  implements CommandLineRunner {

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;
    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private ServiceAuthFeign serviceAuthFeign;

    @Override
    public void run(String... args) throws Exception {
        log.info("初始化加载用户pubKey");
        try {
            Thread.sleep(1000);
            refreshUserPubKey();
            log.info("初始化加载用户pubKey成功");
        }catch(Exception e){
            log.error("初始化加载用户pubKey失败,1分钟后自动重试!",e);
        }
        log.info("初始化加载客户端pubKey");
        try {
            Thread.sleep(100);
            refreshServicePubKey();
            log.info("初始化加载客户端pubKey成功");
        }catch(Exception e){
            log.error("初始化加载客户pubKey失败,1分钟后自动重试!",e);
        }
    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshUserPubKey(){
        log.info("refreshUserPubKey start,clientid:{},secret:{}",serviceAuthConfig.getClientId(),serviceAuthConfig.getClientSecret());
        BaseResponse resp = serviceAuthFeign.getUserPublicKey(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getStatus() == HttpStatus.OK.value()) {
            ObjectRestResponse<byte[]> userResponse = (ObjectRestResponse<byte[]>) resp;
            this.userAuthConfig.setPubKeyByte(userResponse.getData());
            log.info("refreshUserPubKey success,clientid:{},secret:{}",serviceAuthConfig.getClientId(),serviceAuthConfig.getClientSecret());
        }

    }
    @Scheduled(cron = "0 0/1 * * * ?")
    public void refreshServicePubKey(){
        log.info("refreshServicePubKey start,clientid:{},secret:{}",serviceAuthConfig.getClientId(),serviceAuthConfig.getClientSecret());
        BaseResponse resp = serviceAuthFeign.getServicePublicKey(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getStatus() == HttpStatus.OK.value()) {
            ObjectRestResponse<byte[]> userResponse = (ObjectRestResponse<byte[]>) resp;
            this.serviceAuthConfig.setPubKeyByte(userResponse.getData());
            log.info("refreshServicePubKey success,clientid:{},secret:{}",serviceAuthConfig.getClientId(),serviceAuthConfig.getClientSecret());
        }

    }

}