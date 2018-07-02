package com.qiu.common.controller;

import com.qiu.common.response.ObjectRestResponse;
import com.qiu.common.response.TableResultResponse;
import com.qiu.common.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-03
 **/
@Slf4j
//public class BaseController<biz extends BaseBiz,Entity> {
public class BaseController<bbaseService extends BaseService,Entity> {

    @Autowired
  //  BService baseService;
    private bbaseService baseService;

   // @Autowired
   // private biz baseBiz;

   /* public void setRepository(BaseService<Entity> baseService) {
        this.baseService = baseService;
    }*/

    /**
     * 通用添加
     * @param entity
     * @return
     */
   @RequestMapping(value = "",method = RequestMethod.POST)
   @ResponseBody
   public ObjectRestResponse<Entity> add(@RequestBody Entity entity){
       log.debug("***********Entity:{},value:{}",entity,entity.toString());
       baseService.insertSelective(entity);
       return new ObjectRestResponse<Entity>();
   }

    /**
     * 查询单条记录
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ObjectRestResponse get(@PathVariable long id){
        log.debug("&&&&&&&&&&&&&&&&&&&thyis is come from BaseController");
        ObjectRestResponse<Entity> entityObjectRestResponse = new ObjectRestResponse<>();
        Entity o =(Entity) baseService.selectById(id);
        entityObjectRestResponse.data((Entity)o);
        return entityObjectRestResponse;
    }

    /**
     * 更新
     * @param entity
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public ObjectRestResponse<Entity> update(@RequestBody Entity entity){
        baseService.updateSelectiveById(entity);
        return new ObjectRestResponse<Entity>();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ObjectRestResponse<Entity> remove(@PathVariable Long id){
        baseService.deleteById(id);
        return new ObjectRestResponse<Entity>();
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public List<Entity> all(){
        return baseService.findAll();
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public TableResultResponse<Entity> list(@RequestParam Map<String, Object> params){
        //查询列表数据
        //Query query = new Query(params);
        //return baseBiz.selectByQuery(query);
        return null;

    }
}
