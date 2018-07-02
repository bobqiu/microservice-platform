package com.qiu.security.client.utils;

import com.qiu.common.response.BaseResponse;
import com.qiu.security.client.config.ServiceAuthConfig;
import com.qiu.security.client.feign.ServiceAuthFeign;
import com.qiu.security.common.exception.auth.ClientTokenException;
import com.qiu.security.common.response.ObjectRestResponse;
import com.qiu.security.common.utils.jwt.IJwtInfo;
import com.qiu.security.common.utils.jwt.JwtHelper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-02
 **/
@Configuration
@Slf4j
@EnableScheduling
public class ServiceAuthUtil{
    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    @Autowired
    private ServiceAuthFeign serviceAuthFeign;

    private List<String> allowedClient;
    private String clientToken;


    public IJwtInfo getInfoFromToken(String token) throws Exception {
        try {
            IJwtInfo infoFromToken = JwtHelper.getInfoFromToken(token, serviceAuthConfig.getPubKeyByte());
            return infoFromToken;
        } catch (ExpiredJwtException ex) {
            throw new ClientTokenException("Client token expired!,cleint:"+serviceAuthConfig.getClientId());
        } catch (SignatureException ex) {
            throw new ClientTokenException("Client token signature error!cleint:"+serviceAuthConfig.getClientId());
        } catch (IllegalArgumentException ex) {
            throw new ClientTokenException("Client token is null or empty!cleint:"+serviceAuthConfig.getClientId()+",secret:"+serviceAuthConfig.getClientSecret());
        }
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void refreshAllowedClient() {
        log.debug("refresh allowedClient.....,{}",serviceAuthConfig.toString());
        BaseResponse resp = serviceAuthFeign.getAllowedClient(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        log.debug("============resp:==========={}",resp);
        if (resp.getStatus() == 200) {
            ObjectRestResponse<List<String>> allowedClient = (ObjectRestResponse<List<String>>) resp;
            this.allowedClient = allowedClient.getData();
        }
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    public void refreshClientToken() {
        log.debug("refresh client token.....");
        BaseResponse resp = serviceAuthFeign.getAccessToken(serviceAuthConfig.getClientId(), serviceAuthConfig.getClientSecret());
        if (resp.getStatus() == 200) {
            ObjectRestResponse<String> clientToken = (ObjectRestResponse<String>) resp;
            this.clientToken = clientToken.getData();
        }
    }


    public String getClientToken() {
        if (this.clientToken == null) {
            this.refreshClientToken();

        }
        return clientToken;
    }

    public List<String> getAllowedClient() {
        if (this.allowedClient == null) {
            this.refreshAllowedClient();
        }
        return allowedClient;
    }
}
