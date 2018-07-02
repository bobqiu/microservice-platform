package com.qiu.ratelimiter.user;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-13
 **/
public class DefaultPrincipal implements IUserPrincipal {
    @Override
    public String getName(HttpServletRequest request) {
        if(request.getUserPrincipal()==null) {
            return null;
        }
        return request.getUserPrincipal().getName();
    }
}
