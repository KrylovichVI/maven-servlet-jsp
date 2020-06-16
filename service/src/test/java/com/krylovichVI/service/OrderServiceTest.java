//package com.krylovichVI.service;
//
//import com.krylovichVI.dao.OrderDao;
//import com.krylovichVI.pojo.*;
//import com.krylovichVI.service.impl.DefaultOrderService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class OrderServiceTest {
//    @Mock
//    private OrderDao orderDao;
//
//    @InjectMocks
//    private DefaultOrderService orderService;
//
//    @Test
//    void testOfGetOrder(){
//        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
//        Order order = new Order(authUser, Date.valueOf(LocalDate.now()), Status.CANCELED, "My order");
//        when(orderDao.getOrderById(1L)).thenReturn(order);
//        Order orderById = orderService.getOrderById(1L);
//        assertNotNull(orderById);
//    }
//
//    @Test
//    void testOfEmptyOrdersOfUser(){
//        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
//        when(orderDao.getOrders()).thenReturn(Collections.emptyList());
//        List<Order> ordersOfUser = orderService.getOrdersOfUser(authUser);
//        assertTrue(ordersOfUser.isEmpty());
//    }
//
//    @Test
//    void testEmptyAllOrdersUser(){
//        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
//        when(orderDao.getOrders()).thenReturn(Collections.emptyList());
//        List<Order> orders = orderService.getOrdersOfUser(authUser);
//        assertTrue(orders.isEmpty());
//    }
//
//    @Test
//    void testOfEmptyOrder(){
//        when(orderDao.getOrderById(10L)).thenReturn(null);
//        Order order = orderService.getOrderById(10L);
//        assertNull(order);
//    }
//
//    @Test
//    void testAddOrderOfEmptyAuthUser(){
//        long id = orderService.addOrder("Test", null, null);
//        assertTrue(id == -1 );
//    }
//
//    @Test
//    void testOfUpdateStatusEmptyOrder(){
//        when(orderDao.getOrderById(1L)).thenReturn(null);
//        Long aLong = orderService.updateStatusOrder(1L, Status.IN_PROCESSING.name());
//        assertTrue(aLong == -1L);
//    }
//
//    @Test
//    void testOfUpdateStatusOrder(){
//        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN, null);
//        Order order = new Order(authUser, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "Test Order");
//        when(orderDao.getOrderById(1L)).thenReturn(order);
//        orderService.updateStatusOrder(1L, Status.CANCELED.name());
//        verify(orderDao, times(1)).updateStatusOrder(order);
//    }
//
//    @Test
//    void testOfGetInstance(){
//        OrderService instance = DefaultOrderService.getInstance();
//        assertNotNull(instance);
//    }
//
//    @Test
//    void testOfCountCeil(){
//        when(orderDao.getCountOfRow()).thenReturn(6L);
//        long countOfPage = orderService.getCountOfPage();
//        assertTrue(countOfPage == 2);
//    }
//
//    @Test
//    void testOfCount(){
//        when(orderDao.getCountOfRow()).thenReturn(5L);
//        long countOfPage = orderService.getCountOfPage();
//        assertTrue(countOfPage == 1);
//    }
//
//    @Test
//    void testOfDeleteOrder(){
//        Order order = new Order(null, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "Test");
//        order.setId(1L);
//        when(orderDao.getOrderById(1L)).thenReturn(order);
//        doNothing().when(orderDao).deleteOrder(order);
//        orderService.deleteOrder(order.getId());
//        verify(orderDao, times(1)).deleteOrder(order);
//    }
//}
