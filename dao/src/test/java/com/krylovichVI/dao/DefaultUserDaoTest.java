package com.krylovichVI.dao;

import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.dao.imp.DefaultUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        AuthUser byId = authUser.getById(3L);
        User user = new User("Misha", "Kernasiskiy", "12345667", "test@gmail.com", byId);
        userDao.addUserInfo(user.getAuthUser().getId(), user);
        User userByAuthId = userDao.getUserByAuthId(byId);

        assertNotNull(userByAuthId);
    }

    @Test
    void testUpdateUserInfo(){
        AuthUser byId = authUser.getById(2L);
        User user = new User("M", "K", "1", "test@gmail.com", byId);
        userDao.updateUserInfo(user.getAuthUser().getId(), user);

        User userByAuthId = userDao.getUserByAuthId(byId);

        assertEquals(userByAuthId.getEmail(), user.getEmail());
        assertEquals(userByAuthId.getFirstName(), user.getFirstName());
        assertEquals(userByAuthId.getLastName(), user.getLastName());
        assertEquals(userByAuthId.getPhone(), user.getPhone());
    }
}
