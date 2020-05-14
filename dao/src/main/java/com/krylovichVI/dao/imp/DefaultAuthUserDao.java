package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.utils.SessionUtil;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultAuthUserDao implements AuthUserDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAuthUserDao.class);
    private static AuthUserDao instance;

    private DefaultAuthUserDao() {}

    public static AuthUserDao getInstance(){
        if(instance == null){
            instance = new DefaultAuthUserDao();
        }
        return instance;
    }

    @Override
    public AuthUser getByLogin(String login) {
        try(Session session = SessionUtil.openSession()){
            session.getTransaction().begin();
            AuthUser authUser = (AuthUser)session.createQuery("select a from AuthUser a where a.username = :username")
                    .setParameter("username", login)
                    .getSingleResult();
            session.getTransaction().commit();
            return authUser;
        }
    }

    @Override
    public long saveAuthUser(AuthUser authUser, User userEmpty) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            authUser.setUser(userEmpty);
            userEmpty.setAuthUser(authUser);
            Long id = (Long) session.save(authUser);
            transaction.commit();
            logger.info("auth_user {} save user", authUser.getUsername(), authUser.getPassword(), authUser.getRole().name());
            return id;
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("auth_user {} error by save Auth_User ", authUser.getUsername(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AuthUser> getUsers() {
        Transaction transaction;
        try(Session session = SessionUtil.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            List<AuthUser> listOfAuthUser = session.createQuery("select a from AuthUser a").getResultList();
            transaction.commit();
            return listOfAuthUser;
        }
    }

    @Override
    public AuthUser getById(Long id) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            AuthUser authUser = session.get(AuthUser.class, id);
            transaction.commit();
            return authUser;
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("auth_user {} error by Id ", id, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAuthUser(AuthUser authUser) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(authUser);
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            logger.error("auth_user {} error by delete ", e);
            throw new RuntimeException(e);
        }
    }
}
