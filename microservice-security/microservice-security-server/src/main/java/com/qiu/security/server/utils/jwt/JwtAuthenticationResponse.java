package com.qiu.security.server.utils.jwt;

import java.io.Serializable;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-16
 **/
public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    public JwtAuthenticationResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}
