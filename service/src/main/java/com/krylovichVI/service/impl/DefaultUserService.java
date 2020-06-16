package com.krylovichVI.service.impl;

import com.krylovichVI.dao.UserDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.converters.UserConverter;
import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.UserService;
import org.springframework.transaction.annotation.Transactional;

public class DefaultUserService implements UserService {
    private UserDao userDao;
    private UserConverter userConverter;
    private AuthUserConverter authUserConverter;

    public DefaultUserService(UserDao userDao, UserConverter userConverter, AuthUserConverter authUserConverter) {
        this.userDao = userDao;
        this.userConverter = userConverter;
        this.authUserConverter = authUserConverter;
    }

    @Transactional
    @Override
    public void updateUserInfo(User user) {
        UserEntity userEntity = userConverter.toEntity(user);
        User userByAuthId = userConverter.toDto(userDao.getUserByAuthUser(userEntity.getAuthUser()));
        if(userByAuthId != null){
            userDao.updateUserInfo(userEntity, userByAuthId.getId());
        } else {
            userDao.addUserInfo(userEntity);
        }
    }

    @Transactional
    @Override
    public User getUserByAuthUser(AuthUser authUser) {
        return userConverter.toDto(userDao.getUserByAuthUser(authUserConverter.toEntity(authUser)));
    }
}
