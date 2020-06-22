package com.krylovichVI.service.config;

import com.krylovichVI.dao.config.ConverterConfig;
import com.krylovichVI.dao.config.DaoConfig;
import com.krylovichVI.service.*;
import com.krylovichVI.service.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private DaoConfig daoConfig;

    private ConverterConfig converterConfig;

    public ServiceConfig(DaoConfig daoConfig, ConverterConfig converterConfig) {
        this.daoConfig = daoConfig;
        this.converterConfig = converterConfig;
    }

    @Bean
    public SecurityService securityService(){
        return new DefaultSecurityService(daoConfig.authUserDao(), converterConfig.authUserConverter());
    }

    @Bean
    public AuthUserService authUserService(){
        return new DefaultAuthUserService(
                daoConfig.authUserDao(),
                converterConfig.authUserConverter(),
                converterConfig.userConverter()
        );
    }

    @Bean
    public BlackListService blackListService(){
        return new DefaultBlackListService(
                daoConfig.blackListDao(),
                converterConfig.authUserConverter(),
                converterConfig.blackListConverter()
        );
    }

    @Bean
    public BookService bookService(){
        return new DefaultBookService(
                daoConfig.bookDao(),
                converterConfig.bookConverter()
        );
    }

    @Bean
    public OrderService orderService(){
        return new DefaultOrderService(
                converterConfig.orderConverter(),
                daoConfig.orderDao(),
                converterConfig.authUserConverter(),
                converterConfig.bookConverter(),
                bookService()
        );
    }

    @Bean
    public UserService userService(){
        return new DefaultUserService(
                daoConfig.userDao(),
                converterConfig.userConverter(),
                converterConfig.authUserConverter()
        );
    }
}
