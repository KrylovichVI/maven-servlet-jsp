package com.krylovichVI.service.impl;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.service.AuthUserService;

import java.util.List;


public class DefaultAuthUserService implements AuthUserService {
    private static AuthUserService instance;
    private AuthUserDao authUserDao;

    private DefaultAuthUserService() {
        authUserDao = DefaultAuthUserDao.getInstance();
    }

    public static AuthUserService getInstance() {
        synchronized (AuthUserService.class) {
            if (instance == null) {
                instance = new DefaultAuthUserService();
            }
            return instance;
        }
    }


    @Override
    public AuthUser getByLogin(String login) {
        return authUserDao.getByLogin(login);
    }

    @Override
    public long saveAuthUser(AuthUser authUser) {
        return authUserDao.saveAuthUser(authUser);
    }

    @Override
    public AuthUser login(String username, String password) {
        final AuthUser userAuth = authUserDao.getByLogin(username);
        if(userAuth != null){
            if(userAuth.getPassword().equals(password)){
                return userAuth;
            }
            return null;
       }
       return null;
    }

    @Override
    public List<AuthUser> getUsers() {
        return authUserDao.getUsers();
    }
}
