package com.krylovichVI.service;

import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.pojo.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getOrders();

    List<OrderDTO> getOrdersOfUser(AuthUser authUser);

    long addOrder(String orderName, AuthUser authUser);

    void deleteOrder(Long orderId);

    Long updateStatusOrder(Long id, String status);

    Order getOrderById(Long id);
}
