package com.krylovichVI.service.impl;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.converters.BlackListConverter;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import com.krylovichVI.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class DefaultBlackListService implements BlackListService {

    private BlackListDao blackListDao;
    private AuthUserConverter authUserConverter;
    private BlackListConverter blackListConverter;

    @Autowired
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
            blackListDao.addUserInBlackList(authUserConverter.toEntity(authUser), blackListConverter.toEntity(blackListOfCurrentUser));
        }
    }

    @Transactional
    @Override
    public void deleteUserOfBlackList(AuthUser authUser) {
        blackListDao.deleteUserOfBlackList(blackListConverter.toEntity(authUser.getBlackList()));
    }

    @Transactional
    @Override
    public boolean existUserInBlackList(AuthUser authUser) {
        List<BlackList> usersOfBlackList = blackListConverter.toDto(blackListDao.getUsersOfBlackList());
        for(BlackList blackList : usersOfBlackList){
            if(blackList.getAuthUserId().equals(authUser.getId())){
                return true;
            }
        }
        return false;
    }

    @Transactional
    @Override
    public List<BlackList> getUsersOfBlackList() {
        return blackListConverter.toDto(blackListDao.getUsersOfBlackList());
    }
}
