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
        AuthUserEntity admin = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity user = new UserEntity( "Misha", "Kernasovskiy", "12345667", "test@gmail.com", admin);
        authUser.saveAuthUser(admin, user);
        UserEntity userByAuthId = userDao.getUserByAuthUser(admin);

        assertEquals(userByAuthId, user);

        authUser.deleteAuthUser(admin.getUsername());
    }

    @Test
    void testUpdateUserInfo(){
        AuthUserEntity admin = new AuthUserEntity("A", "A", Role.ADMIN, null);
        UserEntity user = new UserEntity("M", "K", "1", "test@gmail.com", null);
        long idAdmin = authUser.saveAuthUser(admin, user);
        userDao.updateUserInfo(user, idAdmin);

        UserEntity userByAuthId = userDao.getUserByAuthUser(admin);

        assertEquals(userByAuthId.getEmail(), user.getEmail());
        assertEquals(userByAuthId.getFirstName(), user.getFirstName());
        assertEquals(userByAuthId.getLastName(), user.getLastName());
        assertEquals(userByAuthId.getPhone(), user.getPhone());

        authUser.deleteAuthUser(admin.getUsername());
    }

    @Test
    void testOfAddUser(){
        UserEntity user = new UserEntity( "M", "K", "1", "test@gmail.com", null);
        long id = -1;
        id = userDao.addUserInfo(user);
        assertNotEquals(id, -1);
    }
}
