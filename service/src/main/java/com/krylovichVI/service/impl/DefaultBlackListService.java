package com.krylovichVI.service.impl;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.dao.imp.DefaultBlackListDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.dto.BlackListDTO;
import com.krylovichVI.service.BlackListService;

import java.util.List;

public class DefaultBlackListService implements BlackListService {
    private static BlackListService instance;
    private BlackListDao blackListDao;

    private DefaultBlackListService(){
        blackListDao = DefaultBlackListDao.getInstance();
    }

    public static BlackListService getInstance(){
        if(instance == null){
            instance = new DefaultBlackListService();
        }
        return instance;
    }

    @Override
    public long addUserInBlackList(AuthUser authUser) {
        return blackListDao.addUserInBlackList(authUser);
    }

    @Override
    public void deleteUserOfBlackList(AuthUser authUser) {
        blackListDao.deleteUserOfBlackList(authUser);
    }

    @Override
    public boolean existUserInBlackList(AuthUser authUser) {
        return blackListDao.existUserInBlackList(authUser);
    }

    @Override
    public List<BlackListDTO> getUsersOfBlackList() {
        return blackListDao.getUsersOfBlackList();
    }
}
