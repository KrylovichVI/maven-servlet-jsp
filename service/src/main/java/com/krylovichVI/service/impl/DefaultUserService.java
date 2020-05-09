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
    public void updateUserInfo(User user) {
        User userByAuthId = userDao.getUserByAuthUser(user.getAuthUser());
        if(userByAuthId != null){
            userDao.updateUserInfo(user, userByAuthId.getId());
        } else {
            userDao.addUserInfo(user);
        }
    }

    @Override
    public User getUserByAuthUser(AuthUser authUser) {
        return userDao.getUserByAuthUser(authUser);
    }
}
