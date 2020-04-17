package com.krylovichVI.dao;

import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultAuthUserDaoTest {
    private AuthUserDao userDao = DefaultAuthUserDao.getInstance();

    @Test
    void databaseConnection(){
        JDBCConnection instance = JDBCConnection.getInstance();
        Connection connection = instance.getConnection();
        assertNotNull(connection);
    }

    @Test
    void testBySaveAuthUser(){
        AuthUser authUser = new AuthUser("myTest", "123456", Role.USER);
        long id = userDao.saveAuthUser(authUser);
        AuthUser userDaoById = userDao.getById(id);

        assertEquals(authUser.getUsername(),  userDaoById.getUsername());
    }

    @Test
    void testByLogin(){
        AuthUser myTestUser = userDao.getByLogin("myTestUser");
        assertNotNull(myTestUser);
    }

    @Test
    void testById(){
        AuthUser myTestUser = userDao.getByLogin("myTestUser");
        AuthUser userDaoById = userDao.getById(myTestUser.getId());

        assertEquals(myTestUser, userDaoById);
    }

    @Test
    void  testByListUser(){
        userDao.saveAuthUser(new AuthUser("myTestFirstUser", "myTestFirstUser", Role.USER));
        userDao.saveAuthUser(new AuthUser("myTestSecondUser", "myTestSecondUser", Role.USER));
        userDao.saveAuthUser(new AuthUser("myTestThreadUser", "myTestThreadUser", Role.USER));

        List<AuthUser> users = userDao.getUsers();

        assertFalse(users.isEmpty());
    }


}
