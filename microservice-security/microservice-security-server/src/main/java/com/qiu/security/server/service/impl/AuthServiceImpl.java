package com.qiu.security.server.service.impl;

import com.qiu.security.common.utils.jwt.JwtInfo;
import com.qiu.security.server.feign.IUserService;
import com.qiu.security.server.service.AuthService;
import com.qiu.security.server.utils.jwt.JwtAuthenticationRequest;
import com.qiu.security.server.utils.token.JwtTokenUtil;
import com.qiu.security.server.vo.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private JwtTokenUtil jwtTokenUtil;
    private IUserService userService;

    @Autowired
    public AuthServiceImpl(
            JwtTokenUtil jwtTokenUtil,
            IUserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @Override
    public String login(JwtAuthenticationRequest authenticationRequest) throws Exception {
        log.debug("=====================authenticationRequest:{}",authenticationRequest.toString());
        UserInfo info = userService.validate(authenticationRequest);
        String token = "";
        if (!StringUtils.isEmpty(info.getId())) {
            JwtInfo jwtInfo = new JwtInfo(info.getUserName(), info.getId(), info.getName());
            log.debug("===========login:jwtinfo:{}", jwtInfo);
            token = jwtTokenUtil.generateToken(jwtInfo);
        }
        return token;
    }

    @Override
    public void validate(String token) throws Exception {
        jwtTokenUtil.getInfoFromToken(token);
    }

    @Override
    public Boolean invalid(String token) {
        // TODO: 2017/9/11 注销token
        return null;
    }

    @Override
    public String refresh(String oldToken) {
        // TODO: 2017/9/11 刷新token
        return null;
    }
}

