package com.krylovichVI.dao;

import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BlackListEntity;

import java.util.List;

public interface BlackListDao {
    void addUserInBlackList(AuthUserEntity authUser, BlackListEntity blackList);

    void deleteUserOfBlackList(BlackListEntity blackList);

    List<BlackListEntity> getUsersOfBlackList();
}
