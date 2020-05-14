package com.krylovichVI.dao;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Book;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.pojo.Page;

import java.util.List;

public interface OrderDao {
    List<Order> getOrders();

    long addOrder(AuthUser authUser, Order order, List<Book> book);

    void deleteOrder(Order order);

    void updateStatusOrder(Order order);

    Order getOrderById(Long id);

    List<Book> getBookByOrder();

    List<Order> getListOrderByPage(Page page);

    long getCountOfRow();
}
