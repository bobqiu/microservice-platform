package com.qiu.security.common.utils.jwt;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
public interface IJwtInfo {
    /**
     * 获取用户名
     * @return
     */
    String getUniqueName();

    /**
     * 获取用户ID
     * @return
     */
    String getId();

    /**
     * 获取名称
     * @return
     */
    String getName();
}
