package com.krylovichVI.dao;

import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.User;
import com.krylovichVI.pojo.UserInfo;
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
        User userEmpty = new User("", "", new UserInfo("", ""), null);
        long id = userDao.saveAuthUser(authUser, userEmpty);
        AuthUser userDaoById = userDao.getById(id);
        assertEquals(authUser.getUsername(),  userDaoById.getUsername());
        userDao.deleteAuthUser(authUser);
    }

    @Test
    void testByLogin(){
        AuthUser authUser = new AuthUser("myTestUser", "123456", Role.USER, null);
        User userEmpty = new User("", "", new UserInfo("", ""), null);
        userDao.saveAuthUser(authUser, userEmpty);
        AuthUser myTestUser = userDao.getByLogin(authUser.getUsername());
        assertNotNull(myTestUser);
        userDao.deleteAuthUser(myTestUser);
    }

    @Test
    void testById(){
        AuthUser authUser = new AuthUser("myTestUser", "123456", Role.USER, null);
        User userEmpty = new User("", "", new UserInfo("", ""), null);
        userDao.saveAuthUser(authUser, userEmpty);
        AuthUser userDaoById = userDao.getById(authUser.getId());
        assertEquals(authUser, userDaoById);
        userDao.deleteAuthUser(userDaoById);
    }

    @Test
    void  testByListUser(){
        AuthUser authUserFirst = new AuthUser("myTestFirstUser", "myTestFirstUser", Role.USER, null);
        AuthUser authUserSecond = new AuthUser("myTestSecondUser", "myTestSecondUser", Role.USER, null);
        AuthUser authUserThread = new AuthUser("myTestThreadUser", "myTestThreadUser", Role.USER, null);
        User userEmpty1 = new User("", "", new UserInfo("", ""), null);
        User userEmpty2 = new User("", "", new UserInfo("", ""), null);
        User userEmpty3 = new User("", "", new UserInfo("", ""), null);

        userDao.saveAuthUser(authUserFirst, userEmpty1);
        userDao.saveAuthUser(authUserSecond, userEmpty2);
        userDao.saveAuthUser(authUserThread, userEmpty3);

        List<AuthUser> users = userDao.getUsers();

        assertEquals(users.size(), 3);

        userDao.deleteAuthUser(authUserFirst);
        userDao.deleteAuthUser(authUserSecond);
        userDao.deleteAuthUser(authUserThread);
    }
}
