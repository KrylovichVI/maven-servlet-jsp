package com.krylovichVI.service;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.service.impl.DefaultAuthUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        when(authUserDao.getByLogin("admin")).thenReturn(new AuthUser("admin", "admin", Role.ADMIN));
        AuthUser admin = authUserService.getByLogin("admin");
        assertNotNull(admin);
        assertEquals(admin.getPassword(), "admin");
        assertEquals(admin.getUsername(), "admin");
        assertEquals(admin.getRole(), Role.ADMIN);
    }

    @Test
    void testCorrectGetUsers(){
        List<AuthUser> list = new ArrayList<>();
        list.add(new AuthUser("admin", "admin", Role.ADMIN));
        list.add(new AuthUser("user", "user", Role.USER));
        list.add(new AuthUser("myUser", "myAdmin", Role.ADMIN));
        when(authUserDao.getUsers()).thenReturn(list);

        List<AuthUser> userList = authUserService.getUsers();
        assertNotNull(userList);
        assertTrue(list.containsAll(userList));
    }

    @Test
    void testSaveUser(){
        AuthUser authUser = new AuthUser(1L, "admin", "admin", Role.ADMIN);
        when(authUserDao.saveAuthUser(authUser)).thenReturn(authUser.getId());
        long id = authUserService.saveAuthUser(authUser);
        assertNotNull(id);
    }
}
