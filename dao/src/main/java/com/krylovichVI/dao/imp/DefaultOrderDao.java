package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.OrderDao;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.pojo.Page;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class DefaultOrderDao extends DefaultPageDao<OrderEntity> implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOrderDao.class);
    private final SessionFactory sessionFactory;

    public DefaultOrderDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<OrderEntity> getOrders() {
        List<OrderEntity> listOrder = sessionFactory.getCurrentSession()
                .createQuery("select o from OrderEntity o").getResultList();
        return listOrder;
    }

    @Override
    public long addOrder(AuthUserEntity authUser, OrderEntity order, List<BookEntity> book) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.refresh(authUser);
            authUser.getOrderList().add(order);
            order.getBookSet().addAll(book);
            long id = (Long) session.save(order);
            for(BookEntity myBook : book){
                session.refresh(myBook);
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
    public void deleteOrder(OrderEntity order) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(order);
        session.flush();
        logger.info("order {} delete ", order.getName());
    }

    @Override
    public void updateStatusOrder(OrderEntity order) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.clear();
            session.update(order);
            logger.info("order {} update ", order.getId(), order.getStatus());
        } catch (NonUniqueObjectException e) {
            logger.error("order {} error update ", order, order.getStatus(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderEntity getOrderById(Long id) {
        try{
            OrderEntity order = sessionFactory.getCurrentSession().get(OrderEntity.class, id);
            logger.info("order {} get order by ", id);
            return order;
        } catch (NoResultException e){
            logger.error("order {} error get order by ", id);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookEntity> getBookByOrder() {
        String sql = "Select b.id as id, b.name as name, b.author as author, b.filename as filename  from books as b " +
                "inner join order_book as ob on  b.id = ob.bookId " +
                "inner join orders as o on o.id = ob.orderId where o.status ='IN_PROCESSING'";
        List<BookEntity> resultList = sessionFactory.getCurrentSession().createNativeQuery(sql, BookEntity.class).getResultList();
        return resultList;
    }

    @Override
    public List<OrderEntity> getListOrderByPage(Page page) {
        return super.listOfPage(OrderEntity.class, sessionFactory, page);
    }

    @Override
    public long getCountOfRow() {
        return super.countOfRow(OrderEntity.class, sessionFactory);
    }
}
