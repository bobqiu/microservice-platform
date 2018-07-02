package com.qiu.common.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-19
 **/

//@JsonIdentityInfo(generator = ObjectIdGenerators.UUIDGenerator.class, property = "@id")
//@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@MappedSuperclass
public class BaseEntity  {
    @Id
    @Column(name = "id")
   // @GeneratedValue(strategy= GenerationType.IDENTITY)
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(name="idGenerator",strategy = "com.qiu.common.utils.distributeid.DistributeIdGenerator")
    protected Long id;

    @Transient
    protected String sid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSid() {
        return String.valueOf(id);
    }

    public void setSid(String sid) {
        this.sid = sid;
    }
}
