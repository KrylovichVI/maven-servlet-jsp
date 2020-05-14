package com.krylovichVI.dao;

import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.dao.imp.DefaultBlackListDao;
import com.krylovichVI.pojo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class DefaultBlackListDaoTest {
    private BlackListDao blackListDao;
    private AuthUserDao authUserDao;

    @BeforeEach
    void init(){
        blackListDao = DefaultBlackListDao.getInstance();
        authUserDao = DefaultAuthUserDao.getInstance();
    }

    @Test
    void testByAddUserOfBlackList(){
        AuthUser authUser = new AuthUser("user", "user", Role.USER, null);
        User user = new User("", "", new UserInfo("", ""), null);
        BlackList blackList = new BlackList(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUser, user);
        blackListDao.addUserInBlackList(authUser, blackList);

        assertTrue(existUserOfBlackList(authUser));

        authUserDao.deleteAuthUser(authUser);
    }

    @Test
    void testByDeleteUserOfBlackList(){
        AuthUser authUser = new AuthUser("user", "user", Role.USER, null);
        User user = new User("", "", new UserInfo("", ""), null);
        BlackList blackList = new BlackList(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUser, user);
        blackListDao.addUserInBlackList(authUser, blackList);

        authUserDao.deleteAuthUser(authUser);
        assertFalse(existUserOfBlackList(authUser));
    }

    private boolean existUserOfBlackList(AuthUser authUser) {
        List<BlackList> usersOfBlackList = blackListDao.getUsersOfBlackList();
        for(BlackList list : usersOfBlackList){
            if(list.getAuthUser().equals(authUser)){
                return true;
            }
        }
        return false;
    }

    @Test
    void testOfExistOfBlackList(){
        AuthUser authUser = new AuthUser("user", "user", Role.USER, null);
        User user = new User("", "", new UserInfo("", ""), null);
        BlackList blackList = new BlackList(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUser, user);
        blackListDao.addUserInBlackList(authUser, blackList);
        assertTrue(existUserOfBlackList(authUser));

        authUserDao.deleteAuthUser(authUser);
    }

    @Test
    void testOfNullBlackList(){
        List<BlackList> usersOfBlackList = blackListDao.getUsersOfBlackList();
        assertNotNull(usersOfBlackList);
    }

    @Test
    void testOfUserInBlackList(){
        AuthUser authUserFirst = new AuthUser("user", "user", Role.USER, null);
        User userFirst = new User("", "", new UserInfo("", ""), null);
        BlackList blackListFirst = new BlackList(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUserFirst, userFirst);
        blackListDao.addUserInBlackList(authUserFirst, blackListFirst);

        AuthUser authUserSecond = new AuthUser("admin", "admin", Role.ADMIN, null);
        User userSecond = new User("", "", new UserInfo("", ""), null);
        BlackList blackListSecond = new BlackList(Date.valueOf(LocalDate.now()), null);

        authUserDao.saveAuthUser(authUserSecond, userSecond);
        blackListDao.addUserInBlackList(authUserSecond, blackListSecond);

        List<BlackList> usersOfBlackList = blackListDao.getUsersOfBlackList();

        assertEquals(usersOfBlackList.size(), 2);

        authUserDao.deleteAuthUser(authUserFirst);
        authUserDao.deleteAuthUser(authUserSecond);
    }
}
