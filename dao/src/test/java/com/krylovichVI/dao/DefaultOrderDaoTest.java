package com.krylovichVI.dao;

import com.krylovichVI.dao.imp.DefaultAuthUserDao;
import com.krylovichVI.dao.imp.DefaultOrderDao;
import com.krylovichVI.pojo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DefaultOrderDaoTest {
    private OrderDao orderDao;
    private AuthUserDao authUserDao;

    @BeforeEach
    void init() {
        orderDao = DefaultOrderDao.getInstance();
        authUserDao = DefaultAuthUserDao.getInstance();
    }

    @Test
    void testAddOrder() {
        AuthUser admin = new AuthUser("admin", "admin", Role.ADMIN, null);
        User user = new User("Misha", "Kernasovskiy", new UserInfo("12345667", "test@gmail.com"), null);

        authUserDao.saveAuthUser(admin, user);
        Order testOrder = new Order(admin, Date.valueOf("2020-04-20"), Status.IN_PROCESSING, "TestOrder");
        List<Order> ordersBefore = orderDao.getOrders();
        orderDao.addOrder(admin, testOrder, Collections.emptyList());
        List<Order> ordersAfter = orderDao.getOrders();

        assertEquals(ordersBefore.size() + 1, ordersAfter.size());

        authUserDao.deleteAuthUser(admin);
        orderDao.deleteOrder(testOrder);
    }

    @Test
    void testOfGetOrderById() {
        AuthUser admin = new AuthUser("admin", "admin", Role.ADMIN, null);
        User user = new User("Misha", "Kernasovskiy", new UserInfo("12345667", "test@gmail.com"), null);
        Order testOrder = new Order(admin, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "TestOrderById");

        authUserDao.saveAuthUser(admin, user);
        long id = orderDao.addOrder(admin, testOrder, Collections.emptyList());

        Order orderById = orderDao.getOrderById(id);

        assertEquals(orderById.getAuthUser(), testOrder.getAuthUser());
        assertEquals(orderById.getName(), testOrder.getName());
        assertEquals(orderById.getStatus(), testOrder.getStatus());

        authUserDao.deleteAuthUser(admin);
        orderDao.deleteOrder(testOrder);
    }

    @Test
    void testOfListOrder() {
        AuthUser admin = new AuthUser("admin", "admin", Role.ADMIN, null);
        User user = new User("Misha", "Kernasovskiy", new UserInfo("12345667", "test@gmail.com"), null);
        Order testOrder = new Order(admin, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "TestOrderById");

        authUserDao.saveAuthUser(admin, user);
        orderDao.addOrder(admin, testOrder, Collections.emptyList());
        List<Order> ordersOfBefore = orderDao.getOrders();

        assertFalse(ordersOfBefore.isEmpty());

        authUserDao.deleteAuthUser(admin);
        orderDao.deleteOrder(testOrder);
    }

    @Test
    void testOfDeleteOrder() {
        AuthUser admin = new AuthUser("admin", "admin", Role.ADMIN, null);
        User user = new User("Misha", "Kernasovskiy", new UserInfo("12345667", "test@gmail.com"), null);
        Order testOrder = new Order(admin, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "TestOrderById");

        List<Order> ordersBefore = orderDao.getOrders();

        authUserDao.saveAuthUser(admin, user);
        orderDao.addOrder(admin, testOrder, Collections.emptyList());
        orderDao.deleteOrder(testOrder);
        List<Order> ordersAfter = orderDao.getOrders();

        assertEquals(ordersBefore.size(), ordersAfter.size());

        authUserDao.deleteAuthUser(admin);
    }

    @Test
    void testAddFailOrder(){
        Order testOrder = new Order(null, null, null, null);

        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            orderDao.addOrder(null, testOrder, Collections.emptyList());
        });
        assertNotNull(thrown);
    }

    @Test
    void testOfUpdateStatus(){
        AuthUser admin = new AuthUser("admin", "admin", Role.ADMIN, null);
        User user = new User("Misha", "Kernasovskiy", new UserInfo("12345667", "test@gmail.com"), null);
        Order testOrder = new Order(admin, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "TestOrderById");

        authUserDao.saveAuthUser(admin, user);
        orderDao.addOrder(admin, testOrder, Collections.emptyList());
        testOrder.setStatus(Status.CANCELED);
        orderDao.updateStatusOrder(testOrder);

        assertEquals(orderDao.getOrderById(testOrder.getId()).getStatus(), Status.CANCELED);

        authUserDao.deleteAuthUser(admin);
        orderDao.deleteOrder(testOrder);
    }
}