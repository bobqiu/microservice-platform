package com.qiu.common.api.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-19
 **/
@Data
public class UserInfo implements Serializable {
    public String id;
    public String userName;
    public String passWord;
    public String name;
    private String description;


}
