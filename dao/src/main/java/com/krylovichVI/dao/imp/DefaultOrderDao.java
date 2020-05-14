package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.OrderDao;
import com.krylovichVI.dao.utils.SessionUtil;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.pojo.Page;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DefaultOrderDao extends DefaultPageDao<Order> implements OrderDao {
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
    public long addOrder(AuthUser authUser, Order order, List<Book> book) {
        Transaction transaction = null;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            session.lock(authUser, LockMode.UPGRADE_NOWAIT);
            authUser.getOrderList().add(order);
            order.getBookSet().addAll(book);
            long id = (Long) session.save(order);
            for(Book myBook : book){
                session.lock(myBook, LockMode.UPGRADE_NOWAIT);
                myBook.getOrderList().add(order);
            }
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

    @Override
    public Order getOrderById(Long id) {
        Transaction transaction;
        try(Session session = SessionUtil.openSession()){
            transaction = session.getTransaction();
            transaction.begin();
            Order order = session.get(Order.class, id);
            transaction.commit();
            return order;
        }
    }

    @Override
    public List<Book> getBookByOrder() {
        String sql = "Select b.id as id, b.name as name, b.author as author  from books as b " +
                "inner join order_book as ob on  b.id = ob.bookId " +
                "inner join orders as o on o.id = ob.orderId where o.status ='IN_PROCESSING'";
        try(Session session = SessionUtil.openSession()){
            session.getTransaction().begin();
            List<Book> resultList = session.createNativeQuery(sql, Book.class).getResultList();
            session.getTransaction().commit();
            return resultList;
        }
    }

    @Override
    public List<Order> getListOrderByPage(Page page) {
        return super.listOfPage(Order.class, page);
    }

    @Override
    public long getCountOfRow() {
        return super.countOfRow(Order.class);
    }
}
