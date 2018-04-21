package com.qiu.microserviceconsumerh5ribbon.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: bobqiu
 * @create: 2018-04-19
 **/
@Configuration
@EnableWebMvc
public class MVCConfiguration extends WebMvcConfigurerAdapter {
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){

        return new MappingJackson2HttpMessageConverter();
    }


    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter){
        RequestMappingHandlerAdapter requestMappingHandlerAdapter=new RequestMappingHandlerAdapter();
        List<HttpMessageConverter<?>> messageConverters=new ArrayList<>();
        messageConverters.add(mappingJackson2HttpMessageConverter);
        requestMappingHandlerAdapter.setMessageConverters(messageConverters);
        return requestMappingHandlerAdapter;
    }



}
