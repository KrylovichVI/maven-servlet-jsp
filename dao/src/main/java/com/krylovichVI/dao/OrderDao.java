package com.krylovichVI.dao;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.pojo.dto.OrderDTO;

import java.util.List;

public interface OrderDao {
    List<OrderDTO> getOrders();

    List<OrderDTO> getOrdersOfUser(AuthUser authUser);

    long addOrder(Order order);

    void deleteOrder(Long orderId);

    void updateStatusOrder(Order order);

    Order getOrderById(Long id);
}
