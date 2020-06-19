package com.krylovichVI.service.impl;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.converters.BlackListConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BlackListEntity;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import com.krylovichVI.service.BlackListService;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class DefaultBlackListService implements BlackListService {

    private BlackListDao blackListDao;
    private AuthUserConverter authUserConverter;
    private BlackListConverter blackListConverter;

    public DefaultBlackListService(BlackListDao blackListDao, AuthUserConverter authUserConverter, BlackListConverter blackListConverter){
        this.blackListDao = blackListDao;
        this.authUserConverter = authUserConverter;
        this.blackListConverter = blackListConverter;
    }

    @Transactional
    @Override
    public void addUserInBlackList(AuthUser authUser) {
        boolean existUser = existUserInBlackList(authUser);
        if(!existUser){
            BlackList blackListOfCurrentUser = new BlackList(Date.valueOf(LocalDate.now()), null);

            AuthUserEntity authUserEntity = authUserConverter.toEntity(authUser);
            BlackListEntity blackListEntity = blackListConverter.toEntity(blackListOfCurrentUser);

            blackListDao.addUserInBlackList(authUserEntity, blackListEntity);
        }
    }

    @Transactional
    @Override
    public void deleteUserOfBlackList(AuthUser authUser) {
        BlackListEntity blackListEntity = blackListConverter.toEntity(authUser.getBlackList());
        blackListDao.deleteUserOfBlackList(blackListEntity);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existUserInBlackList(AuthUser authUser) {
        List<BlackListEntity> usersOfBlackListEntity = blackListDao.getUsersOfBlackList();

        List<BlackList> usersOfBlackList = blackListConverter.toDto(usersOfBlackListEntity);
        for(BlackList blackList : usersOfBlackList){
            if(blackList.getAuthUserId().equals(authUser.getId())){
                return true;
            }
        }
        return false;
    }

    @Transactional(readOnly = true)
    @Override
    public List<BlackList> getUsersOfBlackList() {
        return blackListConverter.toDto(blackListDao.getUsersOfBlackList());
    }
}
