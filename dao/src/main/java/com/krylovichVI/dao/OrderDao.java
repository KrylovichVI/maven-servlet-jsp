package com.krylovichVI.dao;

import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.BookEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.pojo.Page;

import java.util.List;

public interface OrderDao {
    List<OrderEntity> getOrders();

    long addOrder(AuthUserEntity authUser, OrderEntity order, List<BookEntity> book);

    void deleteOrder(OrderEntity order);

    void updateStatusOrder(OrderEntity order);

    OrderEntity getOrderById(Long id);

    List<BookEntity> getBookByOrder();

    List<OrderEntity> getListOrderByPage(Page page);

    long getCountOfRow();
}
