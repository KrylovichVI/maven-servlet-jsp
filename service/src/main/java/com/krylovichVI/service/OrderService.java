package com.krylovichVI.service;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    List<Order> getOrdersOfUser(AuthUser authUser);

    long addOrder(String orderName, AuthUser authUser);

    void deleteOrder(Long orderId);

    Long updateStatusOrder(Long id, String status);

    Order getOrderById(Long id);
}
