package com.krylovichVI.service;
import com.krylovichVI.pojo.AuthUser;

import java.util.List;

public interface AuthUserService {
    AuthUser getByLogin(String login);

    long saveAuthUser(String username, String password, String role);

    AuthUser login(String username, String password);

    List<AuthUser> getUsers();

    AuthUser getById(Long authUserId);
}
