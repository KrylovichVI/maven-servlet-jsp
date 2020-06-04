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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfig.class})
@Transactional
public class DefaultUserDaoTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private AuthUserDao authUser;

    @Test
    void testOfAddUserInfo(){
        AuthUser admin = new AuthUser("admin", "admin", Role.ADMIN, null);
        User user = new User("Misha", "Kernasovskiy", new UserInfo("12345667", "test@gmail.com"), admin);
        authUser.saveAuthUser(admin, user);
        User userByAuthId = userDao.getUserByAuthUser(admin);

        assertEquals(userByAuthId, user);

        authUser.deleteAuthUser(admin);
    }

    @Test
    void testUpdateUserInfo(){
        AuthUser admin = new AuthUser("A", "A", Role.ADMIN, null);
        User user = new User("M", "K", new UserInfo("1", "test@gmail.com"), null);
        long idAdmin = authUser.saveAuthUser(admin, user);
        userDao.updateUserInfo(user, idAdmin);

        User userByAuthId = userDao.getUserByAuthUser(admin);

        assertEquals(userByAuthId.getUserInfo().getEmail(), user.getUserInfo().getEmail());
        assertEquals(userByAuthId.getFirstName(), user.getFirstName());
        assertEquals(userByAuthId.getLastName(), user.getLastName());
        assertEquals(userByAuthId.getUserInfo().getPhone(), user.getUserInfo().getPhone());

        authUser.deleteAuthUser(admin);
    }

    @Test
    void testOfAddUser(){
        User user = new User("M", "K", new UserInfo("1", "test@gmail.com"), null);
        long id = -1;
        id = userDao.addUserInfo(user);
        assertNotEquals(id, -1);

    }
}
