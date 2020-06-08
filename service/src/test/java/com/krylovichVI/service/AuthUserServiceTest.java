package com.krylovichVI.service;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.impl.DefaultAuthUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AuthUserServiceTest {
    @Mock
    private AuthUserDao authUserDao;

    @InjectMocks
    private DefaultAuthUserService authUserService;

    @Test
    void testLoginNotExist(){
        when(authUserDao.getByLogin("admin")).thenReturn(null);
        AuthUser admin = authUserService.getByLogin("admin");
        assertNull(admin);
    }

    @Test
    void testLoginCorrect(){
        when(authUserDao.getByLogin("admin")).thenReturn(new AuthUser("admin", "admin", Role.ADMIN, null));
        AuthUser admin = authUserService.getByLogin("admin");
        assertNotNull(admin);
        assertEquals(admin.getPassword(), "admin");
        assertEquals(admin.getUsername(), "admin");
        assertEquals(admin.getRole(), Role.ADMIN);
    }

    @Test
    void testCorrectGetUsers(){
        List<AuthUser> list = new ArrayList<>();
        list.add(new AuthUser("admin", "admin", Role.ADMIN, null));
        list.add(new AuthUser("user", "user", Role.USER, null));
        list.add(new AuthUser("myUser", "myAdmin", Role.ADMIN, null));
        when(authUserDao.getUsers()).thenReturn(list);

        List<AuthUser> userList = authUserService.getUsers();
        assertNotNull(userList);
        assertTrue(list.containsAll(userList));
    }

    @Test
    void testSaveUser(){
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
        User user = new User("", "", new UserInfo("", ""), null);
        when(authUserDao.saveAuthUser(authUser, user)).thenReturn(1L);
        long id = authUserService.saveAuthUser(authUser.getUsername(), authUser.getPassword(), "ADMIN");
        assertEquals(id, 1L);
    }

    @Test
    void testByLogin(){
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
        when(authUserDao.getByLogin("admin")).thenReturn(authUser);
        AuthUser admin = authUserService.login(authUser.getUsername(), authUser.getPassword());

        assertEquals(authUser, admin);
    }

    @Test
    void testByIncorrectUsername(){
        when(authUserDao.getByLogin(anyString())).thenReturn(null);
        AuthUser authUser = authUserService.login("", "");
        assertNull(authUser);
    }
}
