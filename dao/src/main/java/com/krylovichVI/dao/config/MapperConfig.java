package com.krylovichVI.dao.config;

import com.krylovichVI.dao.mapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public AuthUserMapper authUserMapper(){
        return new AuthUserMapperImpl();
    }

    @Bean
    public BlackListMapper blackListMapper(){
        return new BlackListMapperImpl();
    }

    @Bean
    public BookMapper bookMapper(){ return new BookMapperImpl(); }

    @Bean
    public OrderMapper orderMapper(){
        return new OrderMapperImpl();
    }

    @Bean
    public UserMapper userMapper(){
        return new UserMapperImpl();
    }
}
