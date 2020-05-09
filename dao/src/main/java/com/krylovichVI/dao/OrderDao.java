package com.krylovichVI.dao;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrders();

//    List<Order> getOrdersOfUser(AuthUser authUser);

    long addOrder(AuthUser authUser, Order order);

    void deleteOrder(Order order);

    void updateStatusOrder(Order order);

    Order getOrderById(Long id);
}
