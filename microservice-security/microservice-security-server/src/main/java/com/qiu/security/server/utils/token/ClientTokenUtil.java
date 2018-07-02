package com.qiu.security.server.utils.token;

import com.qiu.security.common.utils.jwt.IJwtInfo;
import com.qiu.security.common.utils.jwt.JwtHelper;
import com.qiu.security.server.config.KeyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
@Configuration
public class ClientTokenUtil {
    private Logger logger = LoggerFactory.getLogger(ClientTokenUtil.class);

    @Value("${client.expire}")
    private int expire;
    @Autowired
    private KeyConfig keyConfig;

    public String generateToken(IJwtInfo jwtInfo) throws Exception {
        return JwtHelper.generateToken(jwtInfo, keyConfig.getServicePriKey(), expire);
    }

    public IJwtInfo getInfoFromToken(String token) throws Exception {
        return JwtHelper.getInfoFromToken(token, keyConfig.getServicePubKey());
    }

}

