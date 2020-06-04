package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.OrderDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.pojo.Page;
import org.hibernate.LockMode;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderDao extends DefaultPageDao<Order> implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOrderDao.class);
    private final SessionFactory sessionFactory;

    public DefaultOrderDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> listOrder = (ArrayList<Order>)sessionFactory.getCurrentSession()
                .createQuery("select o from Order o").getResultList();
        return listOrder;
    }

    @Override
    public long addOrder(AuthUser authUser, Order order, List<Book> book) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.lock(authUser, LockMode.UPGRADE_NOWAIT);
            authUser.getOrderList().add(order);
            order.getBookSet().addAll(book);
            long id = (Long) session.save(order);
            for(Book myBook : book){
                session.lock(myBook, LockMode.UPGRADE_NOWAIT);
                myBook.getOrderList().add(order);
            }
            logger.info("order {} add ", order.getAuthUser().getUsername(), order.getName());
            return id;
        } catch (NonUniqueObjectException e) {
            logger.error("order {} error add ", order.getAuthUser().getUsername(), order.getName(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(order);
        session.flush();
        logger.info("order {} delete ", order.getName());
    }

    @Override
    public void updateStatusOrder(Order order) {
        try{
            sessionFactory.getCurrentSession().update(order);
            logger.info("order {} update ", order.getId(), order.getStatus());
        } catch (NonUniqueObjectException e) {
            logger.error("order {} error update ", order, order.getStatus(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order getOrderById(Long id) {
        try{
            Order order = sessionFactory.getCurrentSession().get(Order.class, id);
            logger.info("order {} get order by ", id);
            return order;
        } catch (NoResultException e){
            logger.error("order {} error get order by ", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getBookByOrder() {
        String sql = "Select b.id as id, b.name as name, b.author as author  from books as b " +
                "inner join order_book as ob on  b.id = ob.bookId " +
                "inner join orders as o on o.id = ob.orderId where o.status ='IN_PROCESSING'";
        List<Book> resultList = sessionFactory.getCurrentSession().createNativeQuery(sql, Book.class).getResultList();
        return resultList;
    }

    @Override
    public List<Order> getListOrderByPage(Page page) {
        return super.listOfPage(Order.class, sessionFactory, page);
    }

    @Override
    public long getCountOfRow() {
        return super.countOfRow(Order.class, sessionFactory);
    }
}
