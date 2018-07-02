package com.qiu.security.server.interceptor;

import com.qiu.security.server.config.ClientConfig;
import com.qiu.security.server.service.AuthClientService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
@Slf4j
public class ClientTokenInterceptor implements RequestInterceptor {
    private Logger logger = LoggerFactory.getLogger(ClientTokenInterceptor.class);
    @Autowired
    private ClientConfig clientConfig;
    @Autowired
    private AuthClientService authClientService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        logger.debug("========================ClientTokenInterceptor,start======================");
        try {
            String token = authClientService.apply(clientConfig.getClientId(), clientConfig.getClientSecret());
            String clientTokenHeader = clientConfig.getClientTokenHeader();
            log.debug("@@@@@@@@@@@@@@cleintId:{},header:{}",clientTokenHeader,token);
            requestTemplate.header(clientTokenHeader, token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
