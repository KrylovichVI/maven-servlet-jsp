package com.krylovichVI.service.impl;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.User;
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
    public long saveAuthUser(String username, String password, String role) {
        User userEmpty = new User("", "", new UserInfo("", ""), null);
        AuthUser authUser = new AuthUser(username, password, Role.valueOf(role), null);
        return authUserDao.saveAuthUser(authUser, userEmpty);
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
