package com.krylovichVI.service.impl;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.converters.UserConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.AuthUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultAuthUserService implements AuthUserService {
    private AuthUserDao authUserDao;
    private AuthUserConverter authUserConverter;
    private UserConverter userConverter;

    public DefaultAuthUserService(AuthUserDao authUserDao, AuthUserConverter authUserConverter, UserConverter userConverter) {
        this.authUserDao = authUserDao;
        this.authUserConverter = authUserConverter;
        this.userConverter = userConverter;
    }

    @Transactional(readOnly = true)
    @Override
    public AuthUser getByLogin(String login) {
        return authUserConverter.toDto(authUserDao.getByLogin(login));
    }

    @Transactional
    @Override
    public long saveAuthUser(String username, String password, String role) {
        User userEmpty = new User("", "", "", "", null);
        AuthUser authUser = new AuthUser(username, password, Role.valueOf(role), null);

        AuthUserEntity authUserEntity = authUserConverter.toEntity(authUser);
        UserEntity userEntity = userConverter.toEntity(userEmpty);
        return authUserDao.saveAuthUser(authUserEntity, userEntity);
    }

    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    @Override
    public List<AuthUser> getUsers() {
        return authUserConverter.toDto(authUserDao.getUsers());
    }


    @Transactional
    @Override
    public AuthUser getById(Long authUserId) {
        return authUserConverter.toDto(authUserDao.getById(authUserId));
    }
}
