package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class DefaultAuthUserDao implements AuthUserDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAuthUserDao.class);
    private final SessionFactory factory;

    public DefaultAuthUserDao(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public AuthUser getByLogin(String login) {
        try {
            AuthUser authUser = (AuthUser) factory.getCurrentSession()
                    .createQuery("select a from AuthUser a where a.username = :username")
                    .setParameter("username", login)
                    .getSingleResult();
            return authUser;
        } catch(NoResultException e) {
            logger.error("auth_user not found by login: ", login);
            return null;
        }
    }

    @Override
    public long saveAuthUser(AuthUser authUser, User userEmpty) {
        try {
            Session session = factory.getCurrentSession();
            authUser.setUser(userEmpty);
            userEmpty.setAuthUser(authUser);
            Long id = (Long) session.save(authUser);
            logger.info("auth_user save: ", authUser.getUsername(), authUser.getPassword(), authUser.getRole().name());
            return id;
        } catch(NonUniqueObjectException e){
            logger.error("auth_user error by save ", authUser.getUsername());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AuthUser> getUsers() {
        List<AuthUser> listOfAuthUser = factory.getCurrentSession()
                .createQuery("select a from AuthUser a").getResultList();
        return listOfAuthUser;
    }

    @Override
    public AuthUser getById(Long id) {
        try {
            AuthUser authUser = factory.getCurrentSession().get(AuthUser.class, id);
            logger.info("auth_user save: ", authUser.getUsername(), authUser.getPassword(), authUser.getRole().name());
            return authUser;
        } catch(NoResultException e){
            logger.error("auth_user is not consist: ", id);
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void deleteAuthUser(AuthUser authUser) {
        try {
            AuthUser userFromDb = getByLogin(authUser.getUsername());
            Session session = factory.getCurrentSession();
            session.delete(userFromDb);
            session.flush();
            logger.info("auth_user {} delete user", userFromDb.getUsername(), userFromDb.getPassword(), userFromDb.getRole().name());
        } catch (NoResultException e){
            logger.error("auth_user is delete user: ", authUser.getUsername());
        }
    }
}
