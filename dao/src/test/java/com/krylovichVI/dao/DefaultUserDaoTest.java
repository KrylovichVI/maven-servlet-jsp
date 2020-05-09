package com.krylovichVI.dao;

import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.dao.imp.DefaultUserDao;
import com.krylovichVI.dao.utils.SessionUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
@Disabled
public class DefaultUserDaoTest {
    private UserDao userDao;
    private AuthUserDao authUser;

    @BeforeEach
    void init(){
        userDao = DefaultUserDao.getInstance();
        authUser = DefaultAuthUserDao.getInstance();
    }

    @Test
    void testOfAddUserInfo(){
//        AuthUser admin = new AuthUser("admin", "admin", Role.ADMIN);
//        long idAdmin = authUser.saveAuthUser(admin);
//        User user = new User("Misha", "Kernasovskiy", new UserInfo("12345667", "test@gmail.com"), idAdmin);
//        userDao.addUserInfo(user);
//        User userByAuthId = userDao.getUserByAuthId(idAdmin);
//
//        assertEquals(userByAuthId, user);
//
//        authUser.deleteAuthUser(admin);
//        userDao.deleteUserInfo(user);
    }

    @Test
    void testUpdateUserInfo(){
//        AuthUser admin = new AuthUser("A", "A", Role.ADMIN);
//        long idAdmin = authUser.saveAuthUser(admin);
//        User user = new User("M", "K", new UserInfo("1", "test@gmail.com"), idAdmin);
//        userDao.updateUserInfo(user, idAdmin);
//
//        User userByAuthId = userDao.getUserByAuthId(user.getAuthUser());
//
//        assertEquals(userByAuthId.getUserInfo().getEmail(), user.getUserInfo().getEmail());
//        assertEquals(userByAuthId.getFirstName(), user.getFirstName());
//        assertEquals(userByAuthId.getLastName(), user.getLastName());
//        assertEquals(userByAuthId.getUserInfo().getPhone(), user.getUserInfo().getPhone());
//
//        userDao.deleteUserInfo(userByAuthId);
//        authUser.deleteAuthUser(admin);
    }

    @AfterAll
    void closeSession(){
        SessionUtil.closeSessionFactory();
    }
}
