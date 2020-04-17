package com.krylovichVI.service;
import com.krylovichVI.pojo.AuthUser;

import java.util.List;

public interface AuthUserService {
    AuthUser getByLogin(String login);

    long saveAuthUser(AuthUser authUser);

    AuthUser login(String username, String password);

    List<AuthUser> getUsers();
}
