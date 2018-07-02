package com.qiu.common.response;

import com.qiu.common.constant.RestCodeConstants;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-06
 **/
public class TokenErrorResponse extends BaseResponse {
    public TokenErrorResponse(String message) {
        super(RestCodeConstants.TOKEN_ERROR_CODE, message);
    }
}