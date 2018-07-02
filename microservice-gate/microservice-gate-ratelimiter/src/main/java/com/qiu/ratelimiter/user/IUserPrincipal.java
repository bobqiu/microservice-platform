package com.qiu.ratelimiter.user;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-13
 **/
public interface IUserPrincipal {
    String getName(HttpServletRequest request);
}
