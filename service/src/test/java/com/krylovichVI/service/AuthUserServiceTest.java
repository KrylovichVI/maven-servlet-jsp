package com.krylovichVI.service;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.service.impl.DefaultAuthUserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
@Disabled
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
//        when(authUserDao.getByLogin("admin")).thenReturn(new AuthUser("admin", "admin", Role.ADMIN));
//        AuthUser admin = authUserService.getByLogin("admin");
//        assertNotNull(admin);
//        assertEquals(admin.getPassword(), "admin");
//        assertEquals(admin.getUsername(), "admin");
//        assertEquals(admin.getRole(), Role.ADMIN);
    }

    @Test
    void testCorrectGetUsers(){
//        List<AuthUser> list = new ArrayList<>();
//        list.add(new AuthUser("admin", "admin", Role.ADMIN));
//        list.add(new AuthUser("user", "user", Role.USER));
//        list.add(new AuthUser("myUser", "myAdmin", Role.ADMIN));
//        when(authUserDao.getUsers()).thenReturn(list);
//
//        List<AuthUser> userList = authUserService.getUsers();
//        assertNotNull(userList);
//        assertTrue(list.containsAll(userList));
//
//        authUserDao.deleteAuthUser(list.get(0));
//        authUserDao.deleteAuthUser(list.get(1));
//        authUserDao.deleteAuthUser(list.get(2));
    }

    @Test
    void testSaveUser(){
//        AuthUser authUser = new AuthUser( "admin", "admin", Role.ADMIN);
//        when(authUserDao.saveAuthUser(authUser)).thenReturn(anyLong());
//        long id = authUserService.saveAuthUser(authUser);
//        assertNotNull(id);
    }

    @Test
    void testByLogin(){
//        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN);
//        when(authUserDao.getByLogin("admin")).thenReturn(authUser);
//        AuthUser admin = authUserService.login(authUser.getUsername(), authUser.getPassword());
//
//        assertEquals(authUser, admin);
    }
}
