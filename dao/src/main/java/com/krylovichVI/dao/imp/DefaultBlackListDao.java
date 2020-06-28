package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BlackListEntity;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultBlackListDao implements BlackListDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultBlackListDao.class);
    private final SessionFactory factory;

    public DefaultBlackListDao(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void addUserInBlackList(AuthUserEntity authUser, BlackListEntity blackList) {
        try {
            blackList.setAuthUser(authUser);
            authUser.setBlackList(blackList);
            factory.getCurrentSession().save(blackList);
            logger.info("black list {} add ", blackList.getDateBlock());
        } catch(NonUniqueObjectException e){
            logger.error("black list {} error add ", authUser.getUsername());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserOfBlackList(BlackListEntity blackList) {
        Session session = factory.getCurrentSession();
        session.clear();
        session.delete(blackList);
        session.flush();
        logger.info("black list {} delete ", blackList.getId());
    }

    @Override
    public List<BlackListEntity> getUsersOfBlackList() {
        String sql = "select b from BlackListEntity b";
        List<BlackListEntity> resultList = factory.getCurrentSession().createQuery(sql).getResultList();
        return resultList;
    }
}



