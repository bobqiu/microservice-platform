package com.qiu.security.common.utils;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-30
 **/
public class StringHelper {
    public static String getObjectValue(Object obj){
        return obj==null?"":obj.toString();
    }
}
