package com.qiu.microserviceconsumerh5.entity;

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
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }
}
