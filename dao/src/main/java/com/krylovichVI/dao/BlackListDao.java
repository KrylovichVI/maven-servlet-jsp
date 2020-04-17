package com.krylovichVI.dao;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.dto.BlackListDTO;

import java.util.List;

public interface BlackListDao {
    long addUserInBlackList(AuthUser authUser);

    void deleteUserOfBlackList(AuthUser authUser);

    boolean existUserInBlackList(AuthUser authUser);

    List<BlackListDTO> getUsersOfBlackList();
}
