package com.krylovichVI.service;

import com.krylovichVI.dao.OrderDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.Status;
import com.krylovichVI.pojo.dto.OrderDTO;
import com.krylovichVI.service.impl.DefaultOrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderDao orderDao;

    @InjectMocks
    private DefaultOrderService orderService;

    @Test
    void testOfGetOrder(){
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN);
        Order order = new Order(authUser, Date.valueOf(LocalDate.now()), Status.CANCELED, "My order");
        when(orderDao.getOrderById(1L)).thenReturn(order);
        Order orderById = orderService.getOrderById(1L);
        assertNotNull(orderById);
    }

    @Test
    void testOfEmptyOrdersOfUser(){
        AuthUser authUser = new AuthUser("admin", "admin", Role.ADMIN);
        when(orderDao.getOrdersOfUser(authUser)).thenReturn(null);
        List<OrderDTO> ordersOfUser = orderService.getOrdersOfUser(authUser);
        assertNull(ordersOfUser);
    }

    @Test
    void testEmptyAllOrders(){
        when(orderDao.getOrders()).thenReturn(null);
        List<OrderDTO> orders = orderService.getOrders();
        assertNull(orders);
    }

    @Test
    void testOfEmptyOrder(){
        when(orderDao.getOrderById(10L)).thenReturn(null);
        Order order = orderService.getOrderById(10L);
        assertNull(order);
    }

    @Test
    void testReturnAdminOrders(){
        List<OrderDTO> orderDTOS = new ArrayList<>();
        orderDTOS.add(new OrderDTO(1L, "My Order", Date.valueOf(LocalDate.now()), Status.CANCELED));
        orderDTOS.add(new OrderDTO(2L, "My Order2", Date.valueOf(LocalDate.now()), Status.IN_PROCESSING));
        when(orderDao.getOrders()).thenReturn(orderDTOS);
        List<OrderDTO> orders = orderService.getOrders();

        assertEquals(orders.size(), orderDTOS.size());
    }

    @Test
    void testByDeleteUser(){
        doNothing().when(orderDao).deleteOrder(anyLong());
        orderService.deleteOrder(1L);
        verify(orderDao, times(1)).deleteOrder(1L);
    }
}
