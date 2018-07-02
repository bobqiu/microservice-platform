package com.qiu.security.server.service;

import com.qiu.security.server.utils.jwt.JwtAuthenticationRequest;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
public interface AuthService {
    String login(JwtAuthenticationRequest authenticationRequest) throws Exception;
    String refresh(String oldToken);
    void validate(String token) throws Exception;
    Boolean invalid(String token);
}
