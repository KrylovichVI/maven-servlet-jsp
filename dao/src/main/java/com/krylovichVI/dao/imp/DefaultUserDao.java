package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.UserDao;
import com.krylovichVI.dao.utils.SessionUtil;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DefaultUserDao implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);
    private static UserDao instance;

    private DefaultUserDao() {
    }

    public static UserDao getInstance(){
        if(instance == null){
            instance = new DefaultUserDao();
        }
        return instance;
    }

    @Override
    public long addUserInfo( User user) {
        Transaction transaction = null;
        try (Session session = SessionUtil.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Long id = (Long) session.save(user);
            logger.info("user {} {} add info {}", user.getFirstName(), user.getLastName(), user.getUserInfo());
            transaction.commit();
            return id;
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("user {} error add info ", user.getFirstName(), user.getLastName(), user.getUserInfo(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserInfo(User user, Long id) {
        String sql = "UPDATE User u SET u.firstName = :firstName, u.lastName = :lastName," +
                " u.userInfo.phone = :phone, u.userInfo.email = :email where u.id = :id";
        Transaction transaction = null;
        try (Session session = SessionUtil.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            session.createQuery(sql)
                    .setParameter("firstName", user.getFirstName())
                    .setParameter("lastName", user.getLastName())
                    .setParameter("email", user.getUserInfo().getEmail())
                    .setParameter("phone", user.getUserInfo().getPhone())
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
            logger.info("user {} update info ", user.getFirstName(), user.getLastName(), user.getUserInfo());
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("user {} error update info ", user.getFirstName(), user.getLastName(), user.getUserInfo(), e);
            throw new RuntimeException(e);
        }
    }


    @Override
    public void deleteUserInfo(User user) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.delete(user);
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            logger.error("user {} error delete user ", user.getFirstName(), user.getLastName(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User getUserByAuthUser(AuthUser authUser) {
        Transaction transaction = null;
        String sql = "SELECT u from User u where u.authUser = :authUser";
        try(Session session = SessionUtil.openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            List<User> user = (List<User>) session.createQuery(sql).setParameter("authUser", authUser).getResultList();
            transaction.commit();
            if (user.isEmpty()){
                return null;
            } else {
                return user.get(0);
            }
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("user {} error get by Id info ", authUser, e);
            throw new RuntimeException(e);
        }
    }
}
