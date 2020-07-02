package com.krylovichVI.service;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.service.impl.DefaultSecurityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Transactional
public class SecurityServiceTest {
    @Mock
    private AuthUserDao authUserDao;
    @Mock
    private AuthUserConverter authUserConverter;

    @InjectMocks
    private DefaultSecurityService securityService;

    @Test
    public void testOfNullAuthUser(){
        AuthUser authUser = new AuthUser(1L, "admin", "admin", Role.ADMIN, null, null, Collections.emptyList());
        when(authUserDao.getByLogin(authUser.getUsername())).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> securityService.login(authUser.getUsername(), authUser.getPassword()));
    }

    @Test
    public void testOfCorrectData(){
        AuthUser authUser = new AuthUser(1L, "admin", "admin", Role.ADMIN, null, null, Collections.emptyList());
        AuthUserEntity authUserEntity = new AuthUserEntity("admin", "admin", Role.ADMIN, null);

        when(authUserDao.getByLogin(authUser.getUsername())).thenReturn(authUserEntity);
        when(authUserConverter.toDto(authUserEntity)).thenReturn(authUser);

        AuthUser user = securityService.login(authUser.getUsername(), authUser.getPassword());

        assertEquals(user.getUsername(), authUser.getUsername());
        assertEquals(user.getRole(), authUser.getRole());
        assertEquals(user.getPassword(), authUser.getPassword());
    }
}
