package com.krylovichVI.service.impl;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.converters.UserConverter;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DefaultAuthUserService implements AuthUserService {
    private AuthUserDao authUserDao;
    private AuthUserConverter authUserConverter;
    private UserConverter userConverter;

    @Autowired
    public DefaultAuthUserService(AuthUserDao authUserDao, AuthUserConverter authUserConverter, UserConverter userConverter) {
        this.authUserDao = authUserDao;
        this.authUserConverter = authUserConverter;
        this.userConverter = userConverter;
    }

    @Transactional
    @Override
    public AuthUser getByLogin(String login) {
        return authUserConverter.toDto(authUserDao.getByLogin(login));
    }

    @Transactional
    @Override
    public long saveAuthUser(String username, String password, String role) {
        User userEmpty = new User("", "", "", "", null);
        AuthUser authUser = new AuthUser(username, password, Role.valueOf(role), null);

        return authUserDao.saveAuthUser(authUserConverter.toEntity(authUser), userConverter.toEntity(userEmpty));
    }

    @Transactional
    @Override
    public AuthUser login(String username, String password) {
        final AuthUser userAuth = authUserConverter.toDto(authUserDao.getByLogin(username));
        if(userAuth != null){
            if(userAuth.getPassword().equals(password)){
                return userAuth;
            }
            return null;
       }
       return null;
    }

    @Transactional
    @Override
    public List<AuthUser> getUsers() {
        return authUserConverter.toDto(authUserDao.getUsers());
    }
}
