package com.qiu.zuul.feign;

import com.qiu.common.vo.LogInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-06
 **/
@FeignClient("microservice-provider-userservice")
public interface ILogService {
    @RequestMapping(value="/api/log/save",method = RequestMethod.POST)
    public void saveLog(LogInfo info);
}
