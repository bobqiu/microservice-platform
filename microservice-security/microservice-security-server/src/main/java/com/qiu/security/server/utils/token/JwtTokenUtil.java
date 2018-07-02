package com.qiu.security.server.utils.token;

import com.qiu.security.common.utils.jwt.IJwtInfo;
import com.qiu.security.common.utils.jwt.JwtHelper;
import com.qiu.security.server.config.KeyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
@Component
public class JwtTokenUtil {
    @Value("${jwt.expire}")
    private int expire;
    @Autowired
    private KeyConfig keyConfig;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public String generateToken(IJwtInfo jwtInfo) throws Exception {
        return JwtHelper.generateToken(jwtInfo, keyConfig.getUserPriKey(),expire);
    }

    public IJwtInfo getInfoFromToken(String token) throws Exception {
        return JwtHelper.getInfoFromToken(token, keyConfig.getUserPubKey());
    }
}
