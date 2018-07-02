package com.qiu.microserviceprovideruserservice.dao;

import com.qiu.common.api.vo.UserInfo;
import com.qiu.microserviceprovideruserservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-18
 **/
@Repository
public interface UserRepository  extends JpaRepository<User,Long> {


    /**
     *  and
     * @param id
     * @param name
     * @return
     */
    User findByIdAndName(Long id, String name);


    User findByUserName(String name);

    /**
     *  or
     * @param id
     * @param name
     * @return
     */
    User findByIdOrName(Long id, String name);


    /**
     * between
     * @param start
     * @param end
     * @return
     */
    List<User> findByCrtTimeBetween(Date start, Date end);


    /**
     * lessThan
     * @param start
     * @return
     */
    List<User> getByCrtTimeLessThan(Date start);

    /**
     * Greater Than
     * @param start
     * @return
     */
    List<User> findByCrtTimeGreaterThan(Date start);


    /**
     * is null
     * @return
     */
    List<User> findByNameIsNull();


    /**
     * in
     * @param nameList
     * @return
     */
    List<User> findByNameIn(Collection<String> nameList);

    User getByUserName(String username);
}
