package com.qiu.security.server.controller;

import com.qiu.security.common.response.ObjectRestResponse;
import com.qiu.security.server.config.KeyConfig;
import com.qiu.security.server.model.Client;
import com.qiu.security.server.service.AuthClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-16
 **/
@RestController
@RequestMapping("client")
@Slf4j
public class ClientController{
    @Autowired
    private AuthClientService authClientService;
    @Autowired
    private KeyConfig keyConfiguration;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ObjectRestResponse getAccessToken(String clientId, String secret) throws Exception {
        String token = authClientService.apply(clientId, secret);
        log.debug("!!!!!!!!!!getAccessToken,clientId:{},token:{}",clientId,token);
        return new ObjectRestResponse<String>().data(token);
    }

    @RequestMapping(value = "/myClient")
    public ObjectRestResponse getAllowedClient(String serviceId, String secret) {
        List<String> allowedClients = authClientService.getAllowedClients(serviceId, secret);
        return new ObjectRestResponse<List<String>>().data(allowedClients);
    }

    @RequestMapping(value = "/servicePubKey",method = RequestMethod.POST)
    public ObjectRestResponse<byte[]> getServicePublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
        authClientService.validate(clientId, secret);
        return new ObjectRestResponse<byte[]>().data(keyConfiguration.getServicePubKey());
    }

    @RequestMapping(value = "/userPubKey",method = RequestMethod.POST)
    public ObjectRestResponse<byte[]> getUserPublicKey(@RequestParam("clientId") String clientId, @RequestParam("secret") String secret) throws Exception {
        authClientService.validate(clientId, secret);
        byte[] userPubKey = keyConfiguration.getUserPubKey();
        return new ObjectRestResponse<byte[]>().data(userPubKey);
    }


}
