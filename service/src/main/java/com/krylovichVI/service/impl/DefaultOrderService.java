package com.krylovichVI.service.impl;

import com.krylovichVI.dao.OrderDao;
import com.krylovichVI.dao.imp.DefaultOrderDao;
import com.krylovichVI.pojo.*;
import com.krylovichVI.service.BookService;
import com.krylovichVI.service.OrderService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderService implements OrderService {
    private static OrderService instance;
    private OrderDao orderDao;
    private BookService bookService;
    private final int MAX_ELEMENT_OF_PAGE = 5;


    private DefaultOrderService() {
        orderDao = DefaultOrderDao.getInstance();
        bookService = DefaultBookService.getInstance();
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new DefaultOrderService();
        }
        return instance;
    }

    @Override
    public List<Order> getOrdersOfUser(AuthUser authUser) {
        List<Order> orders = orderDao.getOrders();
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getAuthUser().equals(authUser)) {
                result.add(order);
            }
        }
        return result;
    }

    @Override
    public long addOrder(String orderName, AuthUser authUser, List<Book> book) {
        if (authUser != null) {
            if (orderName.isEmpty()) {
                orderName = "Default order";
            }
            Order order = new Order(authUser, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, orderName);
            return orderDao.addOrder(authUser, order, book);
        } else {
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
        if (orderById != null) {
            orderById.setStatus(Status.valueOf(status));
            orderById.setDateUpdate(Date.valueOf(LocalDate.now()));
            orderDao.updateStatusOrder(orderById);
            return orderById.getId();
        } else {
            return -1L;
        }
    }

    @Override
    public Order getOrderById(Long id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public List<Book> getBookByOrder() {
        List<Book> bookByOrder = orderDao.getBookByOrder();
        List<Book> allBooks = bookService.getBooks();

        if(!bookByOrder.isEmpty()) {
            allBooks.removeAll(bookByOrder);
        }
        return allBooks;
    }

    @Override
    public List<Order> getOrderByPage(int currentPage) {
        List<Order> orders = orderDao.getListOrderByPage(new Page(currentPage, MAX_ELEMENT_OF_PAGE));
        if(!orders.isEmpty()){
            return orders;
        } else {
            return null;
        }
    }

    @Override
    public long getCountOfPage() {
        long countOfRow = orderDao.getCountOfRow();
        if(countOfRow % MAX_ELEMENT_OF_PAGE != 0){
            return (int) Math.ceil((double) orderDao.getCountOfRow() / MAX_ELEMENT_OF_PAGE);
        } else {
            return countOfRow / MAX_ELEMENT_OF_PAGE;
        }
    }

}
