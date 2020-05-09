package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.dao.utils.SessionUtil;
import com.krylovichVI.pojo.BlackList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultBlackListDao implements BlackListDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultBlackListDao.class);
    private static BlackListDao instance;

    private DefaultBlackListDao(){

    }

    public static BlackListDao getInstance(){
        if(instance == null){
            instance = new DefaultBlackListDao();
        }
        return instance;
    }

    @Override
    public void addUserInBlackList(BlackList blackList) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.save(blackList);
            logger.info("black list {} add ", blackList.getDateBlock());
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("black list {} error add ", blackList.getDateBlock(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteUserOfBlackList(BlackList blackList) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.remove(blackList);
            transaction.commit();
            logger.info("black list {} delete ", blackList.getId());
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("black list {} error delete ", blackList.getId(), e);
            throw new RuntimeException(e);
        }
    }


//    @Override
//    public void updateBlackList(BlackList blackList) {
//        Transaction transaction = null;
//        try(Session session = SessionUtil.openSession()){
//            transaction = session.getTransaction();
//            transaction.begin();
//            session.update(blackList);
//            transaction.commit();
//        } catch (HibernateException e){
//            transaction.rollback();
//            logger.error("black list {} error update ", blackList.getId(), e);
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public List<BlackList> getUsersOfBlackList() {
        String sql = "select b from BlackList b";
        Transaction transaction;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            List<BlackList> resultList = session.createQuery(sql).getResultList();
            transaction.commit();
            return resultList;
        }
    }
}



