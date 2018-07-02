package com.qiu.security.server.service.impl;

import com.qiu.security.common.exception.auth.ClientInvalidException;
import com.qiu.security.common.utils.UUIDUtils;
import com.qiu.security.server.dao.ClientDao;
import com.qiu.security.server.model.Client;
import com.qiu.security.server.service.AuthClientService;
import com.qiu.security.server.utils.token.ClientTokenUtil;
import com.qiu.security.server.vo.ClientInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-01
 **/
@Service
public class DbAuthClientServiceImpl implements AuthClientService {
    private Logger logger = LoggerFactory.getLogger(DbAuthClientServiceImpl.class);
    @Autowired
    private ClientDao clientDao;
    @Autowired
    private ClientTokenUtil clientTokenUtil;
    @Autowired
    private DiscoveryClient discovery;
    private ApplicationContext context;

    @Autowired
    public DbAuthClientServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public String apply(String clientId, String secret) throws Exception {
        Client client = getClient(clientId, secret);
        String token = clientTokenUtil.generateToken(new ClientInfo(client.getCode(), client.getName(), String.valueOf(client.getId())));
        logger.debug("$$$$$$$$$$$$$$$client:{},token:{}",client.toString(),token);
        return token;
    }

    private Client getClient(String clientId, String secret) {
        /*Client client = new Client();
        client.setCode(clientId);*/
        Client client = clientDao.getClientByCode(clientId);
        logger.debug("clientId:{},scret:{}",clientId,secret);
        if(client==null||!client.getSecret().equals(secret)){
            throw new ClientInvalidException("getClient::Client not found or Client secret is error!client:{}"+client.toString()+":"+clientId+":"+secret);
        }
        return client;
    }

    @Override
    public void validate(String clientId, String secret) throws Exception {
        /*Client client = new Client();
        client.setCode(clientId);*/
        Client client = clientDao.getClientByCode(clientId);
        if(client==null||!client.getSecret().equals(secret)){
            throw new ClientInvalidException("validate::Client not found or Client secret is error!client:{},acture:{},{}"+client.toString()+":"+clientId+":"+secret);
        }
    }

    @Override
    public List<String> getAllowedClients(String clientId, String secret) {
        Client info = this.getClient(clientId, secret);
        List<String> clients = clientDao.findByIds( Integer.parseInt(String.valueOf(info.getId())));
        if(clients==null) {
            new ArrayList<String>();
        }
        return clients;
    }

    @Override
    public List<String> getAllowedClients(String serviceId) {
        Client info = getClient(serviceId);
        List<String> clients = clientDao.findByIds(Integer.parseInt(String.valueOf(info.getId())));
        if(clients==null) {
            new ArrayList<String>();
        }
        return clients;
    }

    private Client getClient(String clientId) {
        Client client = new Client();
        client.setCode(clientId);
        client = clientDao.getClientByCode(clientId);
        return client;
    }

    @Override
    @Scheduled(cron = "0 0/1 * * * ?")
    public void registryClient() {
        logger.debug("===================discovery client start=============");
        // 自动注册节点
        discovery.getServices().forEach((name) ->{
            Client client = new Client();
            client.setName(name);
            client.setCode(name);
            Client dbClient = clientDao.findByName(name);
            if(dbClient==null) {
                client.setSecret(UUIDUtils.generateShortUuid());
                clientDao.save(client);
                logger.info("add service success,client:" + client.toString());
            }
        });
    }
}
