package com.qiu.security.client.interceptor;

import com.qiu.security.client.annotation.IgnoreClientToken;
import com.qiu.security.client.config.ServiceAuthConfig;
import com.qiu.security.client.utils.ServiceAuthUtil;
import com.qiu.security.common.exception.auth.ClientForbiddenException;
import com.qiu.security.common.utils.jwt.IJwtInfo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-02
 **/
@Slf4j
public class ServiceAuthRestInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(ServiceAuthRestInterceptor.class);

    @Autowired
    private ServiceAuthUtil serviceAuthUtil;

    @Autowired
    private ServiceAuthConfig serviceAuthConfig;

    private List<String> allowedClient;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 配置该注解，说明不进行服务拦截
        IgnoreClientToken annotation = handlerMethod.getBeanType().getAnnotation(IgnoreClientToken.class);
        if (annotation == null) {
            annotation = handlerMethod.getMethodAnnotation(IgnoreClientToken.class);
        }
        if(annotation!=null) {
            return super.preHandle(request, response, handler);
        }
        log.debug("===================serviceAuthConfig.getTokenHeader():{},client:{},secrect:{}", serviceAuthConfig.getTokenHeader(),serviceAuthConfig.getClientId(),serviceAuthConfig.getClientSecret());
        String token = request.getHeader(serviceAuthConfig.getTokenHeader());
        //String token = request.getParameter("token");
        log.debug("====================token:{}", token);
        IJwtInfo infoFromToken = serviceAuthUtil.getInfoFromToken(token);

        String uniqueName = infoFromToken.getUniqueName();
        for(String client:serviceAuthUtil.getAllowedClient()){
            if(client.equals(uniqueName)){
                return super.preHandle(request, response, handler);
            }
        }
        throw new ClientForbiddenException("Client is Forbidden!");
    }
}
