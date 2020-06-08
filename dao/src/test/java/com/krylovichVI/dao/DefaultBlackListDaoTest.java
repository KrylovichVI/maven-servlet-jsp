package com.krylovichVI.dao;

import com.krylovichVI.dao.config.DaoConfig;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BlackListEntity;
import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.pojo.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfig.class})
@Transactional
public class DefaultBlackListDaoTest {
    @Autowired
    private BlackListDao blackListDao;
    @Autowired
    private AuthUserDao authUserDao;

    @Test
    void testByAddUserOfBlackList(){
        AuthUserEntity authUser = new AuthUserEntity("user", "user", Role.USER, null);
        UserEntity user = new UserEntity("", "", "", "", null);
        BlackListEntity blackList = new BlackListEntity(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUser, user);
        blackListDao.addUserInBlackList(authUser, blackList);

        assertTrue(existUserOfBlackList(authUser));

        authUserDao.deleteAuthUser(authUser);
    }

    @Test
    void testByDeleteUserOfBlackList(){
        AuthUserEntity authUser = new AuthUserEntity("user", "user", Role.USER, null);
        UserEntity user = new UserEntity( "", "", "", "", null);
        BlackListEntity blackList = new BlackListEntity(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUser, user);
        blackListDao.addUserInBlackList(authUser, blackList);

        authUserDao.deleteAuthUser(authUser);
        assertFalse(existUserOfBlackList(authUser));
    }

    private boolean existUserOfBlackList(AuthUserEntity authUser) {
        List<BlackListEntity> usersOfBlackList = blackListDao.getUsersOfBlackList();
        for(BlackListEntity list : usersOfBlackList){
            if(list.getAuthUser().equals(authUser)){
                return true;
            }
        }
        return false;
    }

    @Test
    void testOfExistOfBlackList(){
        AuthUserEntity authUser = new AuthUserEntity("user", "user", Role.USER, null);
        UserEntity user = new UserEntity( "", "", "", "", null);
        BlackListEntity blackList = new BlackListEntity(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUser, user);
        blackListDao.addUserInBlackList(authUser, blackList);
        assertTrue(existUserOfBlackList(authUser));

        authUserDao.deleteAuthUser(authUser);
    }

    @Test
    void testOfNullBlackList(){
        List<BlackListEntity> usersOfBlackList = blackListDao.getUsersOfBlackList();
        assertNotNull(usersOfBlackList);
    }

    @Test
    void testOfUserInBlackList(){
        AuthUserEntity authUserFirst = new AuthUserEntity("user", "user", Role.USER, null);
        UserEntity userFirst = new UserEntity( "", "", "", "", null);
        BlackListEntity blackListFirst = new BlackListEntity(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUserFirst, userFirst);
        blackListDao.addUserInBlackList(authUserFirst, blackListFirst);

        AuthUserEntity authUserSecond = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity userSecond = new UserEntity( "", "", "", "", null);
        BlackListEntity blackListSecond = new BlackListEntity(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUserSecond, userSecond);
        blackListDao.addUserInBlackList(authUserSecond, blackListSecond);

        List<BlackListEntity> usersOfBlackList = blackListDao.getUsersOfBlackList();

        assertEquals(usersOfBlackList.size(), 2);

        authUserDao.deleteAuthUser(authUserFirst);
        authUserDao.deleteAuthUser(authUserSecond);
    }
}
