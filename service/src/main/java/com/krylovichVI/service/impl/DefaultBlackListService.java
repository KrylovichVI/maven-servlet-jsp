package com.krylovichVI.service.impl;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.dao.imp.DefaultBlackListDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import com.krylovichVI.service.BlackListService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class DefaultBlackListService implements BlackListService {
    private static BlackListService instance;
    private BlackListDao blackListDao;
    private BlackList blackList;


    private DefaultBlackListService(){
        blackListDao = DefaultBlackListDao.getInstance();
        blackList = new BlackList();
    }

    public static BlackListService getInstance(){
        if(instance == null){
            instance = new DefaultBlackListService();
        }
        return instance;
    }

    @Override
    public void addUserInBlackList(AuthUser authUser) {
        boolean existUser = existUserInBlackList(authUser);
        if(!existUser){
            BlackList blackListOfCurrentUser = new BlackList(Date.valueOf(LocalDate.now()), authUser);
            authUser.setBlackList(blackListOfCurrentUser);
            blackListDao.addUserInBlackList(blackListOfCurrentUser);
        }
    }

    @Override
    public void deleteUserOfBlackList(AuthUser authUser) {
        blackListDao.deleteUserOfBlackList(authUser.getBlackList());
    }

    @Override
    public boolean existUserInBlackList(AuthUser authUser) {
        List<BlackList> usersOfBlackList = blackListDao.getUsersOfBlackList();
        for(BlackList blackList : usersOfBlackList){
            if(blackList.getAuthUser().equals(authUser)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<BlackList> getUsersOfBlackList() {
        return blackListDao.getUsersOfBlackList();
    }
}
