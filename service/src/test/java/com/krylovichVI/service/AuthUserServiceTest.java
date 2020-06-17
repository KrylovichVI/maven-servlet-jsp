package com.krylovichVI.service;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.converters.UserConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.UserEntity;
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
    @Mock
    private AuthUserConverter authUserConverter;
    @Mock
    private UserConverter userConverter;

    @InjectMocks
    private DefaultAuthUserService authUserService;

    @Test
    void testLoginNotExist(){
        AuthUserEntity authUserEntity = null;
        when(authUserDao.getByLogin("admin")).thenReturn(authUserEntity);
        when(authUserConverter.toDto(authUserEntity)).thenReturn(null);
        AuthUser admin = authUserService.getByLogin("admin");
        assertNull(admin);
    }

    @Test
    void testLoginCorrect(){
        AuthUserEntity authUserEntity = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
        when(authUserDao.getByLogin("admin")).thenReturn(authUserEntity);
        when(authUserConverter.toDto(authUserEntity)).thenReturn(authUser);
        AuthUser admin = authUserService.getByLogin("admin");
        assertNotNull(admin);
        assertEquals(admin.getPassword(), authUser.getPassword());
        assertEquals(admin.getUsername(), authUser.getUsername());
        assertEquals(admin.getRole(), authUser.getRole());
    }

    @Test
    void testCorrectGetUsers(){
        List<AuthUserEntity> listEntity = new ArrayList<>();
        listEntity.add(new AuthUserEntity("admin", "admin", Role.ADMIN, null));
        listEntity.add(new AuthUserEntity("user", "user", Role.USER, null));
        listEntity.add(new AuthUserEntity("myUser", "myAdmin", Role.ADMIN, null));

        List<AuthUser> list = new ArrayList<>();
        list.add(new AuthUser("admin", "admin", Role.ADMIN, null));
        list.add(new AuthUser("user", "user", Role.USER, null));
        list.add(new AuthUser("myUser", "myAdmin", Role.ADMIN, null));

        when(authUserDao.getUsers()).thenReturn(listEntity);
        when(authUserConverter.toDto(listEntity)).thenReturn(list);

        List<AuthUser> userList = authUserService.getUsers();
        assertNotNull(userList);
        assertTrue(list.containsAll(userList));
    }

    @Test
    void testSaveUser(){
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
        User user = new User("", "", "", "", null);

        AuthUserEntity authUserEntity = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity userEntity = new UserEntity("", "", "", "", null);

        when(authUserConverter.toEntity(authUser)).thenReturn(authUserEntity);
        when(userConverter.toEntity(user)).thenReturn(userEntity);
        when(authUserDao.saveAuthUser(authUserEntity, userEntity)).thenReturn(1L);

        long id = authUserService.saveAuthUser(authUser.getUsername(), authUser.getPassword(), "ADMIN");
        assertEquals(id, 1L);
    }

    @Test
    void testByLogin(){
        AuthUserEntity authUserEntity = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);

        when(authUserDao.getByLogin("admin")).thenReturn(authUserEntity);
        when(authUserConverter.toDto(authUserEntity)).thenReturn(authUser);

        AuthUser admin = authUserService.login(authUser.getUsername(), authUser.getPassword());

        assertEquals(authUser, admin);
    }

    @Test
    void testByIncorrectUsername(){
        AuthUserEntity authUserEntity = null;
        AuthUser authUserDto = null;

        when(authUserDao.getByLogin(anyString())).thenReturn(authUserEntity);
        when(authUserConverter.toDto(authUserEntity)).thenReturn(authUserDto);

        AuthUser authUser = authUserService.login("", "");
        assertNull(authUser);
    }
}
