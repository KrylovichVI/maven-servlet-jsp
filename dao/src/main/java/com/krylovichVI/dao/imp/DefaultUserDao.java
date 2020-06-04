package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.UserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
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
    public long addUserInfo( User user) {
        try{
            Long id = (Long) sessionFactory.getCurrentSession().save(user);
            logger.info("user {} {} add info {}", user.getFirstName(), user.getLastName(), user.getUserInfo());
            return id;
        } catch (NoResultException e) {
            logger.error("user {} error add info ", user.getFirstName(), user.getLastName(), user.getUserInfo(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserInfo(User user, Long id) {
        String sql = "UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName," +
                " u.userInfo.phone = :phone, u.userInfo.email = :email where u.id = :id";
        try{
            sessionFactory.getCurrentSession().createQuery(sql)
                    .setParameter("firstName", user.getFirstName())
                    .setParameter("lastName", user.getLastName())
                    .setParameter("email", user.getUserInfo().getEmail())
                    .setParameter("phone", user.getUserInfo().getPhone())
                    .setParameter("id", id)
                    .executeUpdate();
            logger.info("user {} update info ", user.getFirstName(), user.getLastName(), user.getUserInfo());
        } catch (NonUniqueObjectException e) {
            logger.error("user {} error update info ", user.getFirstName(), user.getLastName(), user.getUserInfo(), e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteUserInfo(User user) {
        sessionFactory.getCurrentSession().delete(user);
        logger.info("user {} delete user ", user.getFirstName(), user.getLastName());
    }

    @Override
    public User getUserByAuthUser(AuthUser authUser) {
        String sql = "SELECT u from User u where u.authUser = :authUser";
        List<User> user = (List<User>) sessionFactory.getCurrentSession().createQuery(sql)
                .setParameter("authUser", authUser).getResultList();
        if (user.isEmpty()){
            return null;
        } else {
            return user.get(0);
        }
    }
}
