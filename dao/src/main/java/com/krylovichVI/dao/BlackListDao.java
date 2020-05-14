package com.krylovichVI.dao;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;

import java.util.List;

public interface BlackListDao {
    void addUserInBlackList(AuthUser authUser, BlackList blackList);

    void deleteUserOfBlackList(BlackList blackList);

    List<BlackList> getUsersOfBlackList();
}
