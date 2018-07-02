package com.qiu.security.server.service;

import com.qiu.security.server.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
@Service
public interface AuthClientService {
    public String apply(String clientId, String secret) throws Exception;

    /**
     * 获取授权的客户端列表
     * @param serviceId
     * @param secret
     * @return
     */
    public List<String> getAllowedClients(String serviceId, String secret);

    /**
     * 获取服务授权的客户端列表
     * @param serviceId
     * @return
     */
    public List<String> getAllowedClients(String serviceId);

    public void registryClient();

    public void validate(String clientId, String secret) throws Exception;
}
