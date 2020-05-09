package com.krylovichVI.service;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;

public interface UserService {
    void updateUserInfo(User user);

    User getUserByAuthUser(AuthUser authUser);
}
