package com.qiu.microserviceprovideruserservice.dao;

import com.qiu.microserviceprovideruserservice.entity.GateLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-05
 **/
@Repository
public interface GateLogRepository extends JpaRepository<GateLog,Long> {
}
