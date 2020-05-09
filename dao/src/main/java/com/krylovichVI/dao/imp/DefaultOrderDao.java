package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.OrderDao;
import com.krylovichVI.dao.utils.SessionUtil;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Order;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderDao implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOrderDao.class);
    private static OrderDao instance;

    public static OrderDao getInstance(){
        if(instance == null){
            instance = new DefaultOrderDao();
        }
        return instance;
    }

    @Override
    public List<Order> getOrders() {
        try(Session session = SessionUtil.openSession()) {
            session.getTransaction().begin();
            List<Order> listOrder = (ArrayList<Order>)session.createQuery("select o from Order o").getResultList();
            session.getTransaction().commit();
            return listOrder;
        }
    }

    @Override
    public long addOrder(AuthUser authUser, Order order) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.lock(authUser, LockMode.UPGRADE_NOWAIT);
            authUser.getOrderList().add(order);
            long id = (Long) session.save(order);
            transaction.commit();
            logger.info("order {} add ", order.getAuthUser().getUsername(), order.getName());
            return id;
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("order {} error add ", order.getAuthUser().getUsername(), order.getName(), e);
           throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(Order order) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.remove(order);
            transaction.commit();
            logger.info("order {} delete ", order.getName());
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("order {} error delete ", order.getName(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatusOrder(Order order) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.update(order);
            transaction.commit();
          logger.info("order {} update ", order.getId(), order.getStatus());
        } catch (HibernateException e) {
            transaction.rollback();
            logger.error("order {} error update ", order, order.getStatus(), e);
            throw new RuntimeException(e);
        }
    }

//    @Override
//    public List<Order> getOrdersOfUser(AuthUser authUser) {
//        String sql = "SELECT orders.id, name, auth_user.username, date, status FROM orders " +
//                "inner join auth_user on auth_user.id = orders.auth_user_id where auth_user.username = ?";
//        List<Order> listOrderOfUser = new ArrayList<>();
//        Transaction transaction;
//        try(Session session = SessionUtil.openSession()){
//            transaction = session.getTransaction();
//            transaction.begin();
//            transaction.commit();
//
//            preparedStatement.setString(1, authUser.getUsername());
//
//            try(ResultSet resultSet = preparedStatement.executeQuery()){
//                while (resultSet.next()){
//                    listOrderOfUser.add(new OrderDTO(
//                            resultSet.getLong("id"),
//                            resultSet.getString("name"),
//                            resultSet.getDate("date"),
//                            Status.valueOf(resultSet.getString("status"))
//                            ));
//                }
//            }
//        }
//        return listOrderOfUser;
//    }

    @Override
    public Order getOrderById(Long id) {
        String sql = "select * from orders where orders.id = ?";
        Transaction transaction;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            Order order = session.get(Order.class, id);
            transaction.commit();
            return order;
        }
    }
}
