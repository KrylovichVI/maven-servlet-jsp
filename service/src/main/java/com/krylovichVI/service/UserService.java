package com.krylovichVI.service;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;

public interface UserService {
    long addUserInfo(Long auth_user_id, User user);

    void updateUserInfo(Long id, User user);

    User getUserByAuthId(AuthUser authUser);
}
