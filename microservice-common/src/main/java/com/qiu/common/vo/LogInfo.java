package com.qiu.common.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-06
 **/
@Data
public class LogInfo implements Serializable {
    private String menu;

    private String opt;

    private String uri;


    private Date crtTime;

    private String crtUser;

    private String crtName;

    private String crtHost;


    public LogInfo(String menu, String option, String uri,  Date crtTime, String crtUser, String crtName, String crtHost) {
        this.menu = menu;
        this.opt = option;
        this.uri = uri;
        this.crtTime = crtTime;
        this.crtUser = crtUser;
        this.crtName = crtName;
        this.crtHost = crtHost;
    }

}

