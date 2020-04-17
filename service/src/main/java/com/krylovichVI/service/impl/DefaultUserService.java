package com.krylovichVI.service.impl;

import com.krylovichVI.dao.UserDao;
import com.krylovichVI.dao.imp.DefaultUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.AuthUserService;
import com.krylovichVI.service.UserService;

public class DefaultUserService implements UserService {
    private static UserService instance;
    private UserDao userDao;
    private AuthUserService authUserService;

    private DefaultUserService() {
        userDao = DefaultUserDao.getInstance();
        authUserService = DefaultAuthUserService.getInstance();
    }

    public static UserService getInstance(){
        if(instance == null){
            instance = new DefaultUserService();
        }
        return instance;
    }

    @Override
    public long addUserInfo(Long auth_user_id, User user) {
        return userDao.addUserInfo(auth_user_id, user);
    }

    @Override
    public void updateUserInfo(Long id, User user) {
        userDao.updateUserInfo(id, user);
    }

    @Override
    public User getUserByAuthId(AuthUser authUser) {
        User userByAuthId = userDao.getUserByAuthId(authUser);
        if(userByAuthId == null){
            userDao.addUserInfo(authUser.getId(), new User("", "", "", "", authUser));
            userByAuthId = userDao.getUserByAuthId(authUser);
        }
        return userByAuthId;
    }
}
