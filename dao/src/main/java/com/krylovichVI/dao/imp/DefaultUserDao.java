package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.JDBCConnection;
import com.krylovichVI.dao.UserDao;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DefaultUserDao implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultUserDao.class);
    private static UserDao instance;

    private DefaultUserDao() {
    }

    public static UserDao getInstance(){
        if(instance == null){
            instance = new DefaultUserDao();
        }
        return instance;
    }

    private Connection getConnection() {
        return JDBCConnection.getInstance().getConnection();
    }

    @Override
    public long addUserInfo(Long auth_id, User user) {
        String sql = "INSERT INTO user(first_name, last_name, phone, email, auth_id) VALUES(?,?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            insertAndUpdateSQL(auth_id, user, preparedStatement);
            logger.info("user {} add info ", auth_id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                generatedKeys.next();
                return generatedKeys.getLong(1);
            }

        } catch (SQLException e) {
            logger.error("user {} error add info ", auth_id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUserInfo(Long auth_id, User user) {
        String sql = "UPDATE user SET first_name = ?, last_name = ?, phone = ?, email = ? WHERE auth_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            insertAndUpdateSQL(auth_id, user, preparedStatement);
            logger.info("user {} update info ", auth_id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone());
        } catch (SQLException e) {
            logger.error("user {} error update info ", auth_id, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhone(), e);
            throw new RuntimeException(e);
        }
    }

    private void insertAndUpdateSQL(Long auth_id, User user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getPhone());
        preparedStatement.setString(4, user.getEmail());
        preparedStatement.setLong(5, auth_id);
        preparedStatement.executeUpdate();
    }


    @Override
    public User getUserByAuthId(AuthUser authUser) {
        String sql = "SELECT * FROM user inner join auth_user on auth_id = auth_user.id where auth_user.id = ?";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            preparedStatement.setLong(1, authUser.getId());

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    return new User(
                            resultSet.getLong("id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("phone"),
                            resultSet.getString("email"),
                            authUser
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("user {} error get by Id info ", authUser.getUsername(), e);
            throw new RuntimeException(e);
        }
        return null;
    }
}
