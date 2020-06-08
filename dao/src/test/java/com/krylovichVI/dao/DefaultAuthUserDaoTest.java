package com.krylovichVI.dao;

import com.krylovichVI.dao.config.DaoConfig;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.pojo.Role;
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
        AuthUserEntity authUser = new AuthUserEntity("myTest", "123456", Role.USER, null);
        UserEntity userEmpty = new UserEntity( "", "", "", "", null);
        long id = authUserDao.saveAuthUser(authUser, userEmpty);
        AuthUserEntity userDaoById = authUserDao.getById(id);
        assertEquals(authUser.getUsername(),  userDaoById.getUsername());
        authUserDao.deleteAuthUser(userDaoById);
    }

    @Test
    void testByLogin(){
        AuthUserEntity authUser = new AuthUserEntity("myTestUser", "123456", Role.USER, null);
        UserEntity userEmpty = new UserEntity("", "", "", "", null);
        authUserDao.saveAuthUser(authUser, userEmpty);
        AuthUserEntity myTestUser = authUserDao.getByLogin(authUser.getUsername());
        assertNotNull(myTestUser);
        authUserDao.deleteAuthUser(myTestUser);
    }

    @Test
    void testById(){
        AuthUserEntity authUser = new AuthUserEntity("myTestUser", "123456", Role.USER, null);
        UserEntity userEmpty = new UserEntity("", "", "", "", null);
        authUserDao.saveAuthUser(authUser, userEmpty);
        AuthUserEntity userDaoById = authUserDao.getById(authUser.getId());
        assertEquals(authUser, userDaoById);
        authUserDao.deleteAuthUser(userDaoById);
    }

    @Test
    void  testByListUser(){
        AuthUserEntity authUserFirst = new AuthUserEntity("myTestFirstUser", "myTestFirstUser", Role.USER, null);
        AuthUserEntity authUserSecond = new AuthUserEntity("myTestSecondUser", "myTestSecondUser", Role.USER, null);
        AuthUserEntity authUserThread = new AuthUserEntity("myTestThreadUser", "myTestThreadUser", Role.USER, null);
        UserEntity userEmpty1 = new UserEntity( "", "", "", "", null);
        UserEntity userEmpty2 = new UserEntity( "", "", "", "", null);
        UserEntity userEmpty3 = new UserEntity( "", "", "", "", null);

        authUserDao.saveAuthUser(authUserFirst, userEmpty1);
        authUserDao.saveAuthUser(authUserSecond, userEmpty2);
        authUserDao.saveAuthUser(authUserThread, userEmpty3);

        List<AuthUserEntity> users = authUserDao.getUsers();

        assertEquals(users.size(), 3);

        authUserDao.deleteAuthUser(authUserFirst);
        authUserDao.deleteAuthUser(authUserSecond);
        authUserDao.deleteAuthUser(authUserThread);
    }
}
