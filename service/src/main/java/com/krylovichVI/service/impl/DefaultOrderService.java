package com.krylovichVI.service.impl;

import com.krylovichVI.dao.OrderDao;
import com.krylovichVI.dao.imp.DefaultOrderDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.pojo.Status;
import com.krylovichVI.service.OrderService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderService implements OrderService {
    private static OrderService instance;
    private OrderDao orderDao;


    private DefaultOrderService() {
        orderDao = DefaultOrderDao.getInstance();
    }

    public static OrderService getInstance(){
        if(instance == null){
            instance = new DefaultOrderService();
        }
        return instance;
    }

    @Override
    public List<Order> getOrders() {
        return orderDao.getOrders();
    }

    @Override
    public List<Order> getOrdersOfUser(AuthUser authUser) {
        List<Order> orders = orderDao.getOrders();
        List<Order> result = new ArrayList<>();
        for(Order order : orders){
            if(order.getAuthUser().equals(authUser)){
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public long addOrder(String orderName, AuthUser authUser) {
        if(authUser != null) {
            if(orderName.isEmpty()){
                orderName = "Default order";
            }
            Order order = new Order(authUser, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, orderName);
            return orderDao.addOrder(authUser,order);
        }else {
            return -1;
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderDao.getOrderById(orderId);
        orderDao.deleteOrder(order);
    }

    @Override
    public Long updateStatusOrder(Long id, String status) {
        Order orderById = getOrderById(id);
        if(orderById != null) {
            orderById.setStatus(Status.valueOf(status));
            orderById.setDateUpdate(Date.valueOf(LocalDate.now()));
            orderDao.updateStatusOrder(orderById);
            return orderById.getId();
        }else{
            return -1L;
        }
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }
}
