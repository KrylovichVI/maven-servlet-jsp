package com.krylovichVI.dao;

import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.UserEntity;

public interface UserDao {
    long addUserInfo(UserEntity user);

    void updateUserInfo(UserEntity user, Long id);

    UserEntity getUserByAuthUser(AuthUserEntity authUser);

    void deleteUserInfo(UserEntity user);
}
