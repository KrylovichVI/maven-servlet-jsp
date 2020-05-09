package com.krylovichVI.service;
import com.krylovichVI.dao.UserDao;
import com.krylovichVI.pojo.User;
import com.krylovichVI.service.impl.DefaultUserService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@Disabled
public class UserServiceTest {
    @Mock
    private UserDao userDao;

    @InjectMocks
    private DefaultUserService userService;

    @Test
    void testOfAddUser(){
//        AuthUser authUser = new AuthUser( "admin", "admin", Role.ADMIN);
//        User user = new User("Vitali", "KrylovichVI", new UserInfo("+375291234567", "KrylovichVI@gmail.com"), authUser.getId());
//        when(userDao.addUserInfo(user)).thenReturn(anyLong());
//        long id = userService.addUserInfo(user);
//        assertNotNull(id);
    }

    @Test
    void testByAuthId(){
//        AuthUser authUser = new AuthUser( "admin", "admin", Role.ADMIN);
//        User user = new User("KrylovichVI", "Vitali", new UserInfo("+375291234567", "KrylovichVI@gmail.com"), authUser.getId());
//        when(userDao.getUserByAuthId(authUser.getId())).thenReturn(user);
//        User userByAuthId = userService.getUserByAuthId(authUser.getId());
//        assertEquals(userByAuthId.getId(), user.getId());
    }

    @Test
    @Disabled
    void testOfEmptyUser(){
        doNothing().when(userDao).updateUserInfo(any(), anyLong());
        userService.updateUserInfo(new User());
        verify(userDao, times(1)).updateUserInfo(any(), anyLong());
    }
}
