package com.krylovichVI.service;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.converters.BlackListConverter;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BlackListEntity;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.BlackList;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.service.impl.DefaultBlackListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class BlackListServiceTest {
    @Mock
    private BlackListDao blackListDao;
    @Mock
    private AuthUserConverter authUserConverter;
    @Mock
    private BlackListConverter blackListConverter;

    @InjectMocks
    private DefaultBlackListService blackListService;

    @Test
    void testAddUserInBlackList(){
        AuthUserEntity authUserEntity = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        BlackListEntity blackListEntity = new BlackListEntity(Date.valueOf(LocalDate.now()), null);

        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
        BlackList blackList = new BlackList(Date.valueOf(LocalDate.now()), null);

        when(authUserConverter.toEntity(authUser)).thenReturn(authUserEntity);
        when(blackListConverter.toEntity(blackList)).thenReturn(blackListEntity);
        doNothing().when(blackListDao).addUserInBlackList(authUserEntity, blackListEntity);

        blackListService.addUserInBlackList(authUser);

        verify(blackListDao, times(1)).addUserInBlackList(authUserEntity, blackListEntity);
    }

    @Test
    void testOfListUsers(){
        List<BlackList> blackListDTOS = getBlackLists();
        List<BlackListEntity> blackListEntity = getBlackListEntity();

        when(blackListDao.getUsersOfBlackList()).thenReturn(blackListEntity);
        when(blackListConverter.toDto(blackListEntity)).thenReturn(blackListDTOS);
        List<BlackList> usersOfBlackList = blackListService.getUsersOfBlackList();
        assertTrue(blackListDTOS.containsAll(usersOfBlackList));
    }

    private List<BlackList> getBlackLists() {
        List<BlackList> blackListDTOS = new ArrayList<>();
        AuthUser authUserFirst = new AuthUser("admin1", "admin", Role.ADMIN, null);
        authUserFirst.setId(1L);
        AuthUser authUserSecond = new AuthUser("admin2", "admin", Role.ADMIN, null);
        authUserSecond.setId(2L);
        AuthUser authUserThread = new AuthUser("admin3", "admin", Role.ADMIN, null);
        authUserThread.setId(3L);

        blackListDTOS.add(new BlackList(Date.valueOf(LocalDate.now()), authUserFirst.getId()));
        blackListDTOS.add(new BlackList(Date.valueOf(LocalDate.now()), authUserSecond.getId()));
        blackListDTOS.add(new BlackList(Date.valueOf(LocalDate.now()), authUserThread.getId()));
        return blackListDTOS;
    }

    private List<BlackListEntity> getBlackListEntity(){
        List<BlackListEntity> blackListEntities = new ArrayList<>();
        AuthUserEntity authUserFirst = new AuthUserEntity("admin1", "admin", Role.ADMIN, null);
        authUserFirst.setId(1L);
        AuthUserEntity authUserSecond = new AuthUserEntity("admin2", "admin", Role.ADMIN, null);
        authUserSecond.setId(2L);
        AuthUserEntity authUserThread = new AuthUserEntity("admin3", "admin", Role.ADMIN, null);
        authUserThread.setId(3L);

        blackListEntities.add(new BlackListEntity(Date.valueOf(LocalDate.now()), authUserFirst));
        blackListEntities.add(new BlackListEntity(Date.valueOf(LocalDate.now()), authUserSecond));
        blackListEntities.add(new BlackListEntity(Date.valueOf(LocalDate.now()), authUserThread));
        return blackListEntities;
    }
}
