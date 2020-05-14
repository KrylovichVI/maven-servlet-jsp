package com.krylovichVI.dao;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;

import java.util.List;

public interface AuthUserDao {
    AuthUser getByLogin(String username);

    long saveAuthUser(AuthUser authUser, User userEmpty);

    List<AuthUser> getUsers();

    AuthUser getById(Long id);

    void deleteAuthUser(AuthUser authUser);
}
