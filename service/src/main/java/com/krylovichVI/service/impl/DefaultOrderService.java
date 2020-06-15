package com.krylovichVI.service.impl;

import com.krylovichVI.dao.OrderDao;
import com.krylovichVI.dao.converters.AuthUserConverter;
import com.krylovichVI.dao.converters.BookConverter;
import com.krylovichVI.dao.converters.OrderConverter;
import com.krylovichVI.pojo.*;
import com.krylovichVI.service.BookService;
import com.krylovichVI.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultOrderService implements OrderService {
    private final int MAX_ELEMENT_OF_PAGE = 5;
    private OrderDao orderDao;
    private OrderConverter orderConverter;
    private AuthUserConverter authUserConverter;
    private BookConverter bookConverter;
    private BookService bookService;



    @Autowired
    public DefaultOrderService(OrderConverter orderConverter, OrderDao orderDao, AuthUserConverter authUserConverter, BookConverter bookConverter, BookService bookService) {
        this.orderConverter = orderConverter;
        this.orderDao = orderDao;
        this.authUserConverter = authUserConverter;
        this.bookConverter = bookConverter;
        this.bookService = bookService;
    }


    @Override
    public List<Order> getOrdersOfUser(AuthUser authUser) {
        List<Order> orders = orderConverter.toDto(orderDao.getOrders());
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getAuthUserId().equals(authUser.getId())) {
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
            Order order = new Order(authUser.getId(), Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, orderName);
            return orderDao.addOrder(
                    authUserConverter.toEntity(authUser),
                    orderConverter.toEntity(order),
                    bookConverter.toEntity(book)
            );
        } else {
            return -1;
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        Order order = orderConverter.toDto(orderDao.getOrderById(orderId));
        orderDao.deleteOrder(orderConverter.toEntity(order));
    }

    @Override
    public Long updateStatusOrder(Long id, String status) {
        Order orderById = getOrderById(id);
        if (orderById != null) {
            orderById.setStatus(Status.valueOf(status));
            orderById.setDateUpdate(Date.valueOf(LocalDate.now()));
            orderDao.updateStatusOrder(orderConverter.toEntity(orderById));
            return orderById.getId();
        } else {
            return -1L;
        }
    }

    @Override
    public Order getOrderById(Long id) {
        return orderConverter.toDto(orderDao.getOrderById(id));
    }

    @Override
    public List<Book> getBookByOrder() {
        List<Book> bookByOrder = bookConverter.toDto(orderDao.getBookByOrder());
        List<Book> allBooks = bookService.getBooks();

        if(!bookByOrder.isEmpty()) {
            allBooks.removeAll(bookByOrder);
        }
        return allBooks;
    }

    @Override
    public List<Order> getOrderByPage(int currentPage) {
        List<Order> orders = orderConverter.toDto(orderDao.getListOrderByPage(new Page(currentPage, MAX_ELEMENT_OF_PAGE)));
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
