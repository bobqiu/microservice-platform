package com.qiu.common.response;

import com.qiu.common.constant.RestCodeConstants;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-06
 **/
public class TokenForbiddenResponse  extends BaseResponse {
    public TokenForbiddenResponse(String message) {
        super(RestCodeConstants.TOKEN_FORBIDDEN_CODE, message);
    }
}