package com.krylovichVI.dao;

import com.krylovichVI.pojo.AuthUser;

import java.util.List;

public interface AuthUserDao {
    AuthUser getByLogin(String username);

    long saveAuthUser(AuthUser authUser);

    List<AuthUser> getUsers();

    AuthUser getById(Long id);

    void deleteAuthUser(AuthUser authUser);
}
