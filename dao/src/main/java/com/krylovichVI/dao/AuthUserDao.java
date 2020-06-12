package com.krylovichVI.dao;

import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.UserEntity;

import java.util.List;

public interface AuthUserDao {
    AuthUserEntity getByLogin(String username);

    long saveAuthUser(AuthUserEntity authUser, UserEntity userEmpty);

    List<AuthUserEntity> getUsers();

    AuthUserEntity getById(Long id);

    void deleteAuthUser(String username);
}
