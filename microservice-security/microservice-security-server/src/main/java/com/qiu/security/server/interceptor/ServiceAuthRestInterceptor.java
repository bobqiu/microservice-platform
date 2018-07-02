package com.qiu.security.server.interceptor;

import com.qiu.security.common.exception.auth.ClientForbiddenException;
import com.qiu.security.common.utils.jwt.IJwtInfo;
import com.qiu.security.server.config.ClientConfig;
import com.qiu.security.server.model.Client;
import com.qiu.security.server.service.AuthClientService;
import com.qiu.security.server.utils.token.ClientTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ServiceAuthRestInterceptor.class);

    @Autowired
    private ClientTokenUtil clientTokenUtil;
    @Autowired
    private AuthClientService authClientService;
    @Autowired
    private ClientConfig clientConfig;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.debug("==============ServiceAuthRestInterceptor start==============,client:{}",clientConfig.toString());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String token = request.getHeader(clientConfig.getClientTokenHeader());
        IJwtInfo infoFromToken = clientTokenUtil.getInfoFromToken(token);
        String uniqueName = infoFromToken.getUniqueName();
        String clientId = clientConfig.getClientId();
        List<String> allowedClients = authClientService.getAllowedClients(clientId);
        for(String client: allowedClients){
            if(client.equals(uniqueName)){
                return super.preHandle(request, response, handler);
            }
        }
        throw new ClientForbiddenException("Client is Forbidden! client:"+clientConfig.toString());
    }
}
