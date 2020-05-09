package com.krylovichVI.dao;

import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.dao.utils.SessionUtil;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DefaultAuthUserDaoTest {
    private AuthUserDao userDao;

    @BeforeEach
    void init(){
        userDao = DefaultAuthUserDao.getInstance();
    }

    @Test
    void testBySaveAuthUser(){
        AuthUser authUser = new AuthUser("myTest", "123456", Role.USER, null);
        long id = userDao.saveAuthUser(authUser);
        AuthUser userDaoById = userDao.getById(id);
        assertEquals(authUser.getUsername(),  userDaoById.getUsername());
        userDao.deleteAuthUser(authUser);
    }

    @Test
    void testByLogin(){
        AuthUser authUser = new AuthUser("myTestUser", "123456", Role.USER, null);
        userDao.saveAuthUser(authUser);
        AuthUser myTestUser = userDao.getByLogin(authUser.getUsername());
        assertNotNull(myTestUser);
        userDao.deleteAuthUser(myTestUser);
    }

    @Test
    void testById(){
        AuthUser authUser = new AuthUser("myTestUser", "123456", Role.USER, null);
        userDao.saveAuthUser(authUser);
        AuthUser userDaoById = userDao.getById(authUser.getId());
        assertEquals(authUser, userDaoById);
        userDao.deleteAuthUser(userDaoById);
    }

    @Test
    void  testByListUser(){
        AuthUser authUserFirst = new AuthUser("myTestFirstUser", "myTestFirstUser", Role.USER, null);
        AuthUser authUserSecond = new AuthUser("myTestSecondUser", "myTestSecondUser", Role.USER, null);
        AuthUser authUserThread = new AuthUser("myTestThreadUser", "myTestThreadUser", Role.USER, null);

        userDao.saveAuthUser(authUserFirst);
        userDao.saveAuthUser(authUserSecond);
        userDao.saveAuthUser(authUserThread);

        List<AuthUser> users = userDao.getUsers();

        assertEquals(users.size(), 3);

        userDao.deleteAuthUser(authUserFirst);
        userDao.deleteAuthUser(authUserSecond);
        userDao.deleteAuthUser(authUserThread);
    }

    @AfterAll
    static void closeSession(){
        SessionUtil.closeSessionFactory();
    }
}
