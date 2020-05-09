package com.krylovichVI.dao;

import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.dao.imp.DefaultBlackListDao;
import com.krylovichVI.dao.utils.SessionUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class DefaultBlackListDaoTest {
    private BlackListDao blackListDao;
    private AuthUserDao authUserDao;

    @BeforeEach
    void init(){
        blackListDao = DefaultBlackListDao.getInstance();
        authUserDao = DefaultAuthUserDao.getInstance();
    }

    @Test
    void testByAddUserOfBlackList(){
//        AuthUser userDaoById = authUserDao.getById(2L);
//        blackListDao.addUserInBlackList(userDaoById);
//
//        assertTrue(blackListDao.existUserInBlackList(userDaoById));
    }

    @Test
    void testByDeleteUserOfBlackList(){
//        AuthUser userDaoById = authUserDao.getById(2L);
//
//        if(!blackListDao.existUserInBlackList(userDaoById)){
//            blackListDao.addUserInBlackList(userDaoById);
//        }
//        blackListDao.deleteUserOfBlackList(userDaoById);
//
//        assertTrue(!blackListDao.existUserInBlackList(userDaoById));
    }

    @Test
    void testOfExistOfBlackList(){
//        AuthUser userDaoById = authUserDao.getById(3L);
//        assertFalse(blackListDao.existUserInBlackList(userDaoById));
    }

    @Test
    void testOfCountBlackListUsers(){
//        List<BlackListDTO> usersOfBlackList = blackListDao.getUsersOfBlackList();
//        assertNotNull(usersOfBlackList);
    }

    @AfterAll
    void closeSession(){
        SessionUtil.closeSessionFactory();
    }
}
