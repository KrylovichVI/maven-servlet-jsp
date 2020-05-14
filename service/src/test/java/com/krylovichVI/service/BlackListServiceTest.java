package com.krylovichVI.service;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.service.impl.DefaultBlackListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlackListServiceTest {

    @Mock
    private BlackListDao blackListDao;

    @InjectMocks
    private DefaultBlackListService blackListService;

    @Test
    void testAddUserInBlackList(){
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
        BlackList blackList = new BlackList(Date.valueOf(LocalDate.now()), null);
        doNothing().when(blackListDao).addUserInBlackList(authUser, blackList);

        blackListService.addUserInBlackList(authUser);

        verify(blackListDao, times(1)).addUserInBlackList(authUser, blackList);
    }

    @Test
    void testOfListUsers(){
        List<BlackList> blackListDTOS = new ArrayList<>();
        AuthUser authUserFirst = new AuthUser("admin1", "admin", Role.ADMIN, null);
        AuthUser authUserSecond = new AuthUser("admin2", "admin", Role.ADMIN, null);
        AuthUser authUserThread = new AuthUser("admin3", "admin", Role.ADMIN, null);
        blackListDTOS.add(new BlackList(Date.valueOf(LocalDate.now()), authUserFirst));
        blackListDTOS.add(new BlackList(Date.valueOf(LocalDate.now()), authUserSecond));
        blackListDTOS.add(new BlackList(Date.valueOf(LocalDate.now()), authUserThread));
        when(blackListDao.getUsersOfBlackList()).thenReturn(blackListDTOS);
        List<BlackList> usersOfBlackList = blackListService.getUsersOfBlackList();
        assertTrue(blackListDTOS.containsAll(usersOfBlackList));
    }
}
