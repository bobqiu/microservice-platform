package com.qiu.security.common.exception.auth;

import com.qiu.security.common.constants.CommonConstants;
import com.qiu.security.common.exception.BaseException;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-01
 **/
public class ClientInvalidException extends BaseException {
    public ClientInvalidException(String message) {
        super(message,CommonConstants.EX_CLIENT_INVALID_CODE);
    }
}
