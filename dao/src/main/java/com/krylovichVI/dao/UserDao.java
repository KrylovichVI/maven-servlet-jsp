package com.krylovichVI.dao;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;

public interface UserDao {
    long addUserInfo(User user);

    void updateUserInfo(User user, Long id);

    User getUserByAuthUser(AuthUser authUser);

    void deleteUserInfo(User user);
}
