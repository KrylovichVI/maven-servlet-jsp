package com.krylovichVI.service;

import com.krylovichVI.pojo.AuthUser;

public interface SecurityService {
    AuthUser login(String login, String password);
}
