package com.qiu.microserviceprovideruserservice.dao;

import com.qiu.microserviceprovideruserservice.entity.Menu;
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
public interface MenuRepository extends JpaRepository<Menu,Long>,JpaSpecificationExecutor<Menu> {
    @Query(value = " select distinct t.* from base_resource_authority ra inner join base_menu t on ra.resource_id = t.id and ra.authority_id in (   select group_id from base_group_member where user_id = ?1  union select group_id from base_group_leader where user_id = ?1 ) and ra.authority_type = 'group' and ra.resource_type = 'menu' order by t.id",nativeQuery=true)
    List<Menu> getUserAuthorityMenuByUserId(Long id);

    List<Menu> findByTitleLike(String title);

    Menu findById(long id);
}
