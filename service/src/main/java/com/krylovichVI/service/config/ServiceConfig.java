package com.krylovichVI.service.config;

import com.krylovichVI.dao.config.DaoConfig;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.impl.DefaultAuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ServiceConfig {

    private DaoConfig daoConfig;

    public ServiceConfig(DaoConfig daoConfig) {
        this.daoConfig = daoConfig;
    }

    @Bean
    private AuthUserService authUserService(){
        return new DefaultAuthUserService(authUserDao, authUserMapper, userMapper);
    }
}
