package com.qiu.security.server.dao;

import com.qiu.security.server.model.Client;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-05-01
 **/
public interface ClientDao extends JpaRepository<Client,Integer> {

   // Client selectOne(Client client);


    @Query(value = " SELECT c.code FROM auth_client c INNER JOIN auth_client_service b ON b.client_id = c.id WHERE b.service_id =?1",nativeQuery=true)
    List<String> findByIds(Integer id);
    @Override
    <S extends Client> List<S> findAll(Example<S> example);

    Client getClientByCode(String code);

    @Override
    <S extends Client> S save(S entity);

    //void insert(Client client);

   // List<String> selectAllowedClient(String s);

    /*@Query("select a from auth_client a where a.name=?1")
    Client selectOne(String name);*/
    Client findByName(String s);
}
