//package com.krylovichVI.dao;
//
//import com.krylovichVI.dao.imp.DefaultAuthUserDao;
//import com.krylovichVI.dao.imp.DefaultOrderDao;
//import com.krylovichVI.dao.utils.SessionUtil;
//import com.krylovichVI.pojo.AuthUser;
//import com.krylovichVI.pojo.Order;
//import com.krylovichVI.pojo.Status;
//import com.krylovichVI.pojo.dto.OrderDTO;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//
//import java.sql.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@Disabled
//public class DefaultOrderDaoTest {
//    private OrderDao orderDao;
//    private AuthUserDao authUserDao;
//
//    @BeforeEach
//    void init() {
//        orderDao = DefaultOrderDao.getInstance();
//        authUserDao = DefaultAuthUserDao.getInstance();
//    }
//
//    @Test
//    void testAddOrder() {
//        AuthUser userDaoById = authUserDao.getById(3L);
//        Order testOrder = new Order(userDaoById, Date.valueOf("2020-04-20"), Status.IN_PROCESSING, "TestOrder");
//        List<Order> ordersBefore = orderDao.getOrders();
//        orderDao.addOrder(testOrder);
//        List<Order> ordersAfter = orderDao.getOrders();
//
//        assertEquals(ordersBefore.size() + 1, ordersAfter.size());
//    }
//
//    @Test
//    void testOfGetOrderById() {
//        AuthUser userDaoById = authUserDao.getById(3L);
//        Order testOrder = new Order(userDaoById, Date.valueOf("2020-03-21"), Status.IN_PROCESSING, "TestOrderById");
//
//        long id = orderDao.addOrder(testOrder);
//
//        Order orderById = orderDao.getOrderById(id);
//
//        assertEquals(orderById.getDateCreate(), testOrder.getDateCreate());
//        assertEquals(orderById.getAuthUser(), testOrder.getAuthUser());
//        assertEquals(orderById.getName(), testOrder.getName());
//        assertEquals(orderById.getStatus(), testOrder.getStatus());
//    }
//
//    @Test
//    void testOfUsersOrder() {
//        AuthUser userDaoById = authUserDao.getById(3L);
//        List<OrderDTO> ordersOfUserBefore = orderDao.getOrdersOfUser(userDaoById);
//        orderDao.addOrder(new Order(userDaoById, Date.valueOf("2019-04-12"), Status.CANCELED, "Test order"));
//        List<OrderDTO> ordersOfUserAfter = orderDao.getOrdersOfUser(userDaoById);
//
//        assertEquals(ordersOfUserBefore.size() + 1, ordersOfUserAfter.size());
//    }
//
//    @Test
//    void testOfDeleteOrder() {
//        AuthUser userDaoById = authUserDao.getById(3L);
//        List<OrderDTO> ordersOfUserBefore = orderDao.getOrdersOfUser(userDaoById);
//        long myOrderId = orderDao.addOrder(new Order(userDaoById, Date.valueOf("2019-04-12"), Status.CANCELED, "my order"));
//        orderDao.deleteOrder(myOrderId);
//        List<OrderDTO> ordersOfUserAfter = orderDao.getOrdersOfUser(userDaoById);
//
//        assertEquals(ordersOfUserBefore.size(), ordersOfUserAfter.size());
//    }
//
//    @Test
//    void testAddFailOrder(){
//        Order testOrder = new Order(null, null, null, null);
//
//        Throwable thrown = assertThrows(NullPointerException.class, () -> {
//            orderDao.addOrder(testOrder);
//        });
//        assertNotNull(thrown);
//    }
//
//    @AfterAll
//    void closeSession(){
//        SessionUtil.closeSessionFactory();
//    }
//}