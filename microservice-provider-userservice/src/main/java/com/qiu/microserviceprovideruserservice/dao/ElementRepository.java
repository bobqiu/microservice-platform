package com.qiu.microserviceprovideruserservice.dao;

import com.qiu.common.dao.BaseRepository;
import com.qiu.microserviceprovideruserservice.entity.Element;
import com.qiu.microserviceprovideruserservice.entity.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-06-02
 **/
@Repository
/*public interface ElementRepository extends JpaRepository<Element,Long>,JpaSpecificationExecutor<Element> {*/
public interface ElementRepository extends BaseRepository<Element> {
    //@Query(value = "select distinct t.id, t.code,t.type,t.name,t.uri,t.method,m.title menu_id from base_resource_authority ra inner join base_element t on ra.resource_id=t.id and ra.authority_id in (select group_id from base_group_member where user_id = ?1 union select group_id from base_group_leader where user_id = ?1 ) and ra.authority_type = 'group' and ra.resource_type = 'button' inner join base_menu m  on t.menu_id =m.id",nativeQuery = true)
    @Query(value = "select distinct t.*,m.title menu_id from base_resource_authority ra inner join base_element t on ra.resource_id=t.id and ra.authority_id in (select group_id from base_group_member where user_id = ?1 union select group_id from base_group_leader where user_id = ?1 ) and ra.authority_type = 'group' and ra.resource_type = 'button' inner join base_menu m  on t.menu_id =m.id",nativeQuery = true)
    List<Element> getAuthorityElementByUserId(Long uId,Long sid);

    //Page<Element> findAll(Specification<Menu> specification, Pageable pageable);

    @Query(value = " select distinct t.*,m.title as menu_id from base_element t inner join base_menu m on t.menu_id = m.id",nativeQuery = true)
    List<Element> getAllElementPermissions();
}
