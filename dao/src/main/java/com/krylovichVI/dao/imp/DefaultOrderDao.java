package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.JDBCConnection;
import com.krylovichVI.dao.OrderDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Order;
import com.krylovichVI.pojo.Status;
import com.krylovichVI.pojo.dto.OrderDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultOrderDao implements OrderDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultOrderDao.class);
    private static OrderDao instance;
    private AuthUserDao authUserDao;

    private DefaultOrderDao(){
        authUserDao = DefaultAuthUserDao.getInstance();
    }

    public static OrderDao getInstance(){
        if(instance == null){
            instance = new DefaultOrderDao();
        }
        return instance;
    }

    private Connection getConnection() {
        return JDBCConnection.getInstance().getConnection();
    }

    @Override
    public List<OrderDTO> getOrders() {
        String sql = "SELECT orders.id, name, auth_user.username, date, status FROM orders " +
                "inner join auth_user on auth_user.id = orders.auth_user_id;";
        List<OrderDTO> listOrder = new ArrayList<>();
        try(Connection connection = JDBCConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    listOrder.add(new OrderDTO(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("username"),
                            resultSet.getDate("date"),
                            Status.valueOf(resultSet.getString("status"))
                    ));
                }
            }

        } catch (SQLException e) {
            logger.error("order {} error add ", e);
            throw new RuntimeException(e);
        }
        return listOrder;
    }

    @Override
    public long addOrder(Order order) {
        String sql = "INSERT INTO orders (name, auth_user_id, date) VALUES (?, ?, ?)";
        try(Connection connection = JDBCConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            preparedStatement.setString(1,order.getName());
            preparedStatement.setLong(2, order.getAuthUser().getId());
            preparedStatement.setDate(3, order.getDate());
            preparedStatement.executeUpdate();
            logger.info("order {} add ", order.getAuthUser().getUsername(), order.getName());
            try(ResultSet keys = preparedStatement.getGeneratedKeys()){
                keys.next();
                return keys.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("order {} error add ", order.getAuthUser().getUsername(), order.getName(), e);
           throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteOrder(Long orderId) {
        String sql = "delete from orders where id = ?";
        try(Connection connection = JDBCConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setLong(1, orderId);
            preparedStatement.executeUpdate();
            logger.info("order {} delete ", orderId);
        } catch (SQLException e) {
            logger.error("order {} error delete ", orderId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatusOrder(Order order) {
        String sql = "update orders set status = ? where id = ?";
        try(Connection connection = JDBCConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
          preparedStatement.setString(1, order.getStatus().name());
          preparedStatement.setLong(2, order.getId());
          preparedStatement.executeUpdate();
          logger.info("order {} update ", order.getId(), order.getStatus());
        } catch (SQLException e) {
            logger.error("order {} error update ", order.getId(), order.getStatus(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDTO> getOrdersOfUser(AuthUser authUser) {
        String sql = "SELECT orders.id, name, auth_user.username, date, status FROM orders " +
                "inner join auth_user on auth_user.id = orders.auth_user_id where auth_user.username = ?";
        List<OrderDTO> listOrderOfUser = new ArrayList<>();
        try(Connection connection = JDBCConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1, authUser.getUsername());

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    listOrderOfUser.add(new OrderDTO(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getDate("date"),
                            Status.valueOf(resultSet.getString("status"))
                            ));
                }
            }
        } catch (SQLException e) {
            logger.error("order {} error get orders ", authUser.getUsername(), e);
            throw new RuntimeException(e);
        }
        return listOrderOfUser;
    }

    @Override
    public Order getOrderById(Long id) {
        String sql = "select * from orders where orders.id = ?";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setLong(1, id);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    return new Order(
                            resultSet.getLong("id"),
                            authUserDao.getById(resultSet.getLong("auth_user_id")),
                            resultSet.getDate("date"),
                            Status.valueOf(resultSet.getString("status")),
                            resultSet.getString("name")
                    );
                }
            }

        } catch (SQLException e) {
            logger.error("order {} error get by id ", id, e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
