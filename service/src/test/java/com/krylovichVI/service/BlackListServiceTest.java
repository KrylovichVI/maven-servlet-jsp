package com.krylovichVI.service;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.dto.BlackListDTO;
import com.krylovichVI.service.impl.DefaultBlackListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlackListServiceTest {

    @Mock
    private BlackListDao blackListDao;

    @InjectMocks
    private DefaultBlackListService blackListService;

    @Test
    void testAddUserInBlackList(){
        AuthUser authUser = new AuthUser(1L,"admin", "admin", Role.ADMIN);
        when(blackListDao.addUserInBlackList(authUser)).thenReturn(authUser.getId());

        long id = blackListService.addUserInBlackList(authUser);

        assertEquals(id, authUser.getId());
    }

    @Test
    void testOfListUsers(){
        List<BlackListDTO> blackListDTOS = new ArrayList<>();
        blackListDTOS.add(new BlackListDTO(1L, null, null, null));
        blackListDTOS.add(new BlackListDTO(2L, null, null, null));
        blackListDTOS.add(new BlackListDTO(3L, null, null, null));
        when(blackListDao.getUsersOfBlackList()).thenReturn(blackListDTOS);
        List<BlackListDTO> usersOfBlackList = blackListService.getUsersOfBlackList();
        assertTrue(blackListDTOS.containsAll(usersOfBlackList));
    }
}
