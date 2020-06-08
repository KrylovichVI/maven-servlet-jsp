package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.UserDao;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.UserEntity;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class DefaultUserDao implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);
    private final SessionFactory sessionFactory;

    public DefaultUserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long addUserInfo(UserEntity user) {
        try{
            Long id = (Long) sessionFactory.getCurrentSession().save(user);
            logger.info("user {} {} add info {}", user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail());
            return id;
        } catch (NoResultException e) {
            logger.error("user {} error add info ", user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserInfo(UserEntity user, Long id) {
        String sql = "UPDATE UserEntity u SET u.firstName = :firstName, u.lastName = :lastName," +
                " u.phone = :phone, u.email = :email where u.id = :id";
        try{
            sessionFactory.getCurrentSession().createQuery(sql)
                    .setParameter("firstName", user.getFirstName())
                    .setParameter("lastName", user.getLastName())
                    .setParameter("email", user.getEmail())
                    .setParameter("phone", user.getPhone())
                    .setParameter("id", id)
                    .executeUpdate();
            logger.info("user {} update info ", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
        } catch (NonUniqueObjectException e) {
            logger.error("user {} error update info ", user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteUserInfo(UserEntity user) {
        sessionFactory.getCurrentSession().delete(user);
        logger.info("user {} delete user ", user.getFirstName(), user.getLastName());
    }

    @Override
    public UserEntity getUserByAuthUser(AuthUserEntity authUser) {
        String sql = "SELECT u from UserEntity u where u.authUser = :authUser";
        List<UserEntity> user = sessionFactory.getCurrentSession().createQuery(sql)
                .setParameter("authUser", authUser).getResultList();
        if (user.isEmpty()){
            return null;
        } else {
            return user.get(0);
        }
    }
}
