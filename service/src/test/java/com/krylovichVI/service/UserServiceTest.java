package com.krylovichVI.service;
import com.krylovichVI.dao.UserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.impl.DefaultUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    void testOfAddUser(){
        AuthUser authUser = new AuthUser(1L, "admin", "admin", Role.ADMIN);
        User user = new User("Vitali", "KrylovichVI", "+375291234567", "KrylovichVI", authUser);
        when(userDao.addUserInfo(authUser.getId(), user)).thenReturn(1L);
        long id = userService.addUserInfo(authUser.getId(), user);
        assertNotNull(id);
    }

    @Test
    void testByAuthId(){
        AuthUser authUser = new AuthUser(1L, "admin", "admin", Role.ADMIN);
        User user = new User(1L,"Vitali", "KrylovichVI", "+375291234567", "KrylovichVI", authUser);
        when(userDao.getUserByAuthId(authUser)).thenReturn(user);
        User userByAuthId = userService.getUserByAuthId(authUser);
        assertEquals(userByAuthId.getId(), user.getId());
    }
}
