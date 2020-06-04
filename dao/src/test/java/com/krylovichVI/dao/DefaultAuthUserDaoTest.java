package com.krylovichVI.dao;

import com.krylovichVI.dao.config.DaoConfig;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.User;
import com.krylovichVI.pojo.UserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class DefaultAuthUserDaoTest {
    @Autowired
    private AuthUserDao authUserDao;

    @Test
    void testBySaveAuthUser(){
        AuthUser authUser = new AuthUser("myTest", "123456", Role.USER, null);
        User userEmpty = new User("", "", new UserInfo("", ""), null);
        long id = authUserDao.saveAuthUser(authUser, userEmpty);
        AuthUser userDaoById = authUserDao.getById(id);
        assertEquals(authUser.getUsername(),  userDaoById.getUsername());
        authUserDao.deleteAuthUser(userDaoById);
    }

    @Test
    void testByLogin(){
        AuthUser authUser = new AuthUser("myTestUser", "123456", Role.USER, null);
        User userEmpty = new User("", "", new UserInfo("", ""), null);
        authUserDao.saveAuthUser(authUser, userEmpty);
        AuthUser myTestUser = authUserDao.getByLogin(authUser.getUsername());
        assertNotNull(myTestUser);
        authUserDao.deleteAuthUser(myTestUser);
    }

    @Test
    void testById(){
        AuthUser authUser = new AuthUser("myTestUser", "123456", Role.USER, null);
        User userEmpty = new User("", "", new UserInfo("", ""), null);
        authUserDao.saveAuthUser(authUser, userEmpty);
        AuthUser userDaoById = authUserDao.getById(authUser.getId());
        assertEquals(authUser, userDaoById);
        authUserDao.deleteAuthUser(userDaoById);
    }

    @Test
    void  testByListUser(){
        AuthUser authUserFirst = new AuthUser("myTestFirstUser", "myTestFirstUser", Role.USER, null);
        AuthUser authUserSecond = new AuthUser("myTestSecondUser", "myTestSecondUser", Role.USER, null);
        AuthUser authUserThread = new AuthUser("myTestThreadUser", "myTestThreadUser", Role.USER, null);
        User userEmpty1 = new User("", "", new UserInfo("", ""), null);
        User userEmpty2 = new User("", "", new UserInfo("", ""), null);
        User userEmpty3 = new User("", "", new UserInfo("", ""), null);

        authUserDao.saveAuthUser(authUserFirst, userEmpty1);
        authUserDao.saveAuthUser(authUserSecond, userEmpty2);
        authUserDao.saveAuthUser(authUserThread, userEmpty3);

        List<AuthUser> users = authUserDao.getUsers();

        assertEquals(users.size(), 3);

        authUserDao.deleteAuthUser(authUserFirst);
        authUserDao.deleteAuthUser(authUserSecond);
        authUserDao.deleteAuthUser(authUserThread);
    }
}
