package com.qiu.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-01
 **/
@Data
public class PermissionInfo implements Serializable {
    private String code;
    private String type;
    private String uri;
    private String method;
    private String name;
    private String menu;
}
