//package com.krylovichVI.service;
//
//import com.krylovichVI.dao.UserDao;
//import com.krylovichVI.pojo.AuthUser;
//import com.krylovichVI.pojo.Role;
//import com.krylovichVI.pojo.User;
//import com.krylovichVI.service.impl.DefaultUserService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//public class UserServiceTest {
//    @Mock
//    private UserDao userDao;
//
//    @InjectMocks
//    private DefaultUserService userService;
//
//    @Test
//    void testOfAddEmptyUser(){
//        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
//        User user = new User("", "", new UserInfo("", ""), authUser);
//        when(userDao.getUserByAuthUser(authUser)).thenReturn(null);
//        userService.updateUserInfo(user);
//        verify(userDao, times(1)).addUserInfo(user);
//    }
//
//    @Test
//    void testOfGetUserByAuthUser(){
//        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
//        User user = new User("", "", new UserInfo("", ""), authUser);
//        when(userDao.getUserByAuthUser(authUser)).thenReturn(user);
//        User userByAuthUser = userService.getUserByAuthUser(authUser);
//        assertEquals(userByAuthUser, user);
//    }
//}
