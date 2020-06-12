package com.krylovichVI.dao;

import com.krylovichVI.dao.config.DaoConfig;
import com.krylovichVI.dao.entity.AuthUserEntity;
import com.krylovichVI.dao.entity.OrderEntity;
import com.krylovichVI.dao.entity.UserEntity;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.Status;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Ignore
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoConfig.class})
@Transactional
public class DefaultOrderDaoTest {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private AuthUserDao authUserDao;


    @Test
    void testAddOrder() {
        AuthUserEntity admin = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity user = new UserEntity( "Misha", "Kernasovskiy", "12345667", "test@gmail.com", null);

        authUserDao.saveAuthUser(admin, user);
        OrderEntity testOrder = new OrderEntity(admin, Date.valueOf("2020-04-20"), Status.IN_PROCESSING, "TestOrder");
        List<OrderEntity> ordersBefore = orderDao.getOrders();
        orderDao.addOrder(admin, testOrder, Collections.emptyList());
        List<OrderEntity> ordersAfter = orderDao.getOrders();

        assertEquals(ordersBefore.size() + 1, ordersAfter.size());

        authUserDao.deleteAuthUser(admin.getUsername());
        orderDao.deleteOrder(testOrder);
    }

    @Test
    void testOfGetOrderById() {
        AuthUserEntity admin = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity user = new UserEntity( "Misha", "Kernasovskiy","12345667", "test@gmail.com", null);
        OrderEntity testOrder = new OrderEntity(admin, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "TestOrderById");

        authUserDao.saveAuthUser(admin, user);
        long id = orderDao.addOrder(admin, testOrder, Collections.EMPTY_LIST);

        OrderEntity orderById = orderDao.getOrderById(id);

        assertEquals(orderById.getAuthUser(), testOrder.getAuthUser());
        assertEquals(orderById.getName(), testOrder.getName());
        assertEquals(orderById.getStatus(), testOrder.getStatus());

        authUserDao.deleteAuthUser(admin.getUsername());
        orderDao.deleteOrder(testOrder);
    }

    @Test
    void testOfListOrder() {
        AuthUserEntity admin = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity user = new UserEntity("Misha", "Kernasovskiy", "12345667", "test@gmail.com", null);
        OrderEntity testOrder = new OrderEntity(admin, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "TestOrderById");

        authUserDao.saveAuthUser(admin, user);
        orderDao.addOrder(admin, testOrder, Collections.emptyList());
        List<OrderEntity> ordersOfBefore = orderDao.getOrders();

        assertFalse(ordersOfBefore.isEmpty());

        authUserDao.deleteAuthUser(admin.getUsername());
        orderDao.deleteOrder(testOrder);
    }

    @Test
    void testOfDeleteOrder() {
        AuthUserEntity admin = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity user = new UserEntity( "Misha", "Kernasovskiy", "12345667", "test@gmail.com", null);
        OrderEntity testOrder = new OrderEntity(admin, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "TestOrderById");

        List<OrderEntity> ordersBefore = orderDao.getOrders();

        authUserDao.saveAuthUser(admin, user);
        orderDao.addOrder(admin, testOrder, Collections.emptyList());
        orderDao.deleteOrder(testOrder);
        List<OrderEntity> ordersAfter = orderDao.getOrders();

        assertEquals(ordersBefore.size(), ordersAfter.size());

        authUserDao.deleteAuthUser(admin.getUsername());
    }

    @Test
    void testAddFailOrder(){
        OrderEntity testOrder = new OrderEntity(null, null, null, null);

        Throwable thrown = assertThrows(NullPointerException.class, () -> {
            orderDao.addOrder(null, testOrder, Collections.emptyList());
        });
        assertNotNull(thrown);
    }

    @Test
    void testOfUpdateStatus(){
        AuthUserEntity admin = new AuthUserEntity("admin", "admin", Role.ADMIN, null);
        UserEntity user = new UserEntity( "Misha", "Kernasovskiy", "12345667", "test@gmail.com", null);
        OrderEntity testOrder = new OrderEntity(admin, Date.valueOf(LocalDate.now()), Status.IN_PROCESSING, "TestOrderById");

        authUserDao.saveAuthUser(admin, user);
        orderDao.addOrder(admin, testOrder, Collections.emptyList());
        testOrder.setStatus(Status.CANCELED);
        orderDao.updateStatusOrder(testOrder);

        assertEquals(orderDao.getOrderById(testOrder.getId()).getStatus(), Status.CANCELED);

        authUserDao.deleteAuthUser(admin.getUsername());
        orderDao.deleteOrder(testOrder);
    }
}