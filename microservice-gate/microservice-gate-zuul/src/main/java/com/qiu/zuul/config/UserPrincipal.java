package com.qiu.zuul.config;

import com.qiu.ratelimiter.user.IUserPrincipal;
import com.qiu.security.client.config.UserAuthConfig;
import com.qiu.security.client.utils.UserAuthUtil;
import com.qiu.security.common.utils.jwt.IJwtInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-14
 **/
public class UserPrincipal implements IUserPrincipal {

    @Autowired
    private UserAuthConfig userAuthConfig;
    @Autowired
    private UserAuthUtil userAuthUtil;

    @Override
    public String getName(HttpServletRequest request) {
        IJwtInfo infoFromToken = getJwtInfo(request);
        return infoFromToken == null ? null : infoFromToken.getUniqueName();
    }

    private IJwtInfo getJwtInfo(HttpServletRequest request) {
        IJwtInfo infoFromToken = null;
        try {
            String authToken = request.getHeader(userAuthConfig.getTokenHeader());
            if(StringUtils.isEmpty(authToken)) {
                infoFromToken = null;
            } else {
                infoFromToken = userAuthUtil.getInfoFromToken(authToken);
            }
        } catch (Exception e) {
            infoFromToken = null;
        }
        return infoFromToken;
    }

}
