package com.krylovichVI.dao.config;

import com.krylovichVI.dao.converters.*;
import com.krylovichVI.dao.repository.AuthUserRepo;
import com.krylovichVI.dao.repository.BookRepo;
import com.krylovichVI.dao.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.krylovichVI.dao.repository")
public class ConverterConfig {
    @Autowired
    public AuthUserRepo authUserRepo;
    @Autowired
    public OrderRepo orderRepo;
    @Autowired
    public BookRepo bookRepo;

    @Bean
    public AuthUserConverter authUserConverter(){
        return new AuthUserConverter();
    }

    @Bean
    public BlackListConverter blackListConverter(){
        return new BlackListConverter();
    }

    @Bean
    public BookConverter bookConverter(){
        return new BookConverter();
    }

    @Bean
    public OrderConverter orderConverter(){
        return new OrderConverter();
    }

    @Bean
    public UserConverter userConverter(){
        return new UserConverter();
    }
}
