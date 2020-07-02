package com.krylovichVI.service;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import com.krylovichVI.pojo.dto.UserOfBlackListDto;

import java.util.List;

public interface BlackListService {
    void addUserInBlackList(AuthUser authUser);

    void deleteUserOfBlackList(AuthUser authUser);

    boolean existUserInBlackList(AuthUser authUser);

    List<BlackList> getUsersOfBlackList();

    List<UserOfBlackListDto> getUserNameById(List<BlackList> usersOfBlackList, AuthUserService authUserService);
}
