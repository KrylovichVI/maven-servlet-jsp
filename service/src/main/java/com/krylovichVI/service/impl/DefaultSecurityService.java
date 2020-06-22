package com.krylovichVI.service.impl;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.service.SecurityService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class DefaultSecurityService implements SecurityService {
    private final AuthUserDao authUserDao;
    private final AuthUserConverter authUserConverter;

    public DefaultSecurityService(AuthUserDao authUserDao, AuthUserConverter authUserConverter) {
        this.authUserDao = authUserDao;
        this.authUserConverter = authUserConverter;
    }

    @Override
    @Transactional
    public AuthUser login(String login, String password) {
        AuthUser authUser = authUserConverter.toDto(authUserDao.getByLogin(login));
        if(authUser == null){
            throw new UsernameNotFoundException("User not found");
        }

        if(authUser.getPassword().equals(password)){
            return authUser;
        }

        throw new UsernameNotFoundException("Password incorrect");
    }
}
