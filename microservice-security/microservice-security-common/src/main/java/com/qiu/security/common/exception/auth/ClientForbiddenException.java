package com.qiu.security.common.exception.auth;

import com.qiu.security.common.constants.CommonConstants;
import com.qiu.security.common.exception.BaseException;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
public class ClientForbiddenException extends BaseException {
    public ClientForbiddenException(String message) {
        super(message, CommonConstants.EX_CLIENT_FORBIDDEN_CODE);
    }

}