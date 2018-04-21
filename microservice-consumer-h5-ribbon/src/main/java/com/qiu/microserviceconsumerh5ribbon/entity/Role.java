package com.qiu.microserviceconsumerh5ribbon.entity;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
public class Role {
    private Long id;

    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}
