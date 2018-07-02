package com.qiu.security.server.vo;

import com.qiu.security.common.utils.jwt.IJwtInfo;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
public class ClientInfo implements IJwtInfo {
    String clientId;
    String name;
    String id;

    public ClientInfo(String clientId, String name,String id) {
        this.clientId = clientId;
        this.name = name;

    }

    @Override
    public String getUniqueName() {
        return clientId;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
