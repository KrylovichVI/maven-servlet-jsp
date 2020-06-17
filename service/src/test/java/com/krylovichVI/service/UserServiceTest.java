package com.krylovichVI.service;

import com.krylovichVI.dao.UserDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.converters.UserConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.impl.DefaultUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserDao userDao;
    @Mock
    private UserConverter userConverter;
    @Mock
    private AuthUserConverter authUserConverter;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    void testOfAddEmptyUser(){
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
        User user = new User("", "", "", "", authUser.getId());
        authUser.setUser(user);

        AuthUserEntity authUserEntity = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity userEntity = new UserEntity("", "", "", "", authUserEntity);
        authUserEntity.setUser(userEntity);

        when(userConverter.toEntity(user)).thenReturn(userEntity);
        when(userDao.getUserByAuthUser(authUserEntity)).thenReturn(null);
        userService.updateUserInfo(user);
        verify(userDao, times(1)).addUserInfo(userEntity);
    }

    @Test
    void testOfGetUserByAuthUser(){
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
        authUser.setId(1L);
        User user = new User("", "", "", "", authUser.getId());
        authUser.setUser(user);


        AuthUserEntity authUserEntity = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity userEntity = new UserEntity("", "", "", "", authUserEntity);
        authUserEntity.setUser(userEntity);


        when(authUserConverter.toEntity(authUser)).thenReturn(authUserEntity);
        when(userDao.getUserByAuthUser(authUserEntity)).thenReturn(userEntity);
        when(userConverter.toDto(userEntity)).thenReturn(user);

        User userByAuthUser = userService.getUserByAuthUser(authUser);
        assertEquals(userByAuthUser, user);
    }
}
