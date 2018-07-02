package com.qiu.microserviceprovideruserservice.biz;

import com.qiu.common.biz.BaseBiz;
import com.qiu.microserviceprovideruserservice.dao.GroupTypeRepository;
import com.qiu.microserviceprovideruserservice.entity.GroupType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-19
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupTypeBiz extends BaseBiz<GroupTypeRepository,GroupType> {
}
