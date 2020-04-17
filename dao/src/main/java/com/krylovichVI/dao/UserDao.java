package com.krylovichVI.dao;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;

public interface UserDao {
    long addUserInfo(Long auth_user_id, User user);

    void updateUserInfo(Long id, User user);

    User getUserByAuthId(AuthUser authUser);
}
