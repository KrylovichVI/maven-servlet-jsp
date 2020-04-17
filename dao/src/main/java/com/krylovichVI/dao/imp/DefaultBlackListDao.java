package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.BlackListDao;
import com.krylovichVI.dao.JDBCConnection;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import com.krylovichVI.pojo.dto.BlackListDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultBlackListDao implements BlackListDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultBlackListDao.class);
    private static BlackListDao instance;

    private DefaultBlackListDao(){

    }

    public static BlackListDao getInstance(){
        if(instance == null){
            instance = new DefaultBlackListDao();
        }
        return instance;
    }

    private Connection getConnection() {
        return JDBCConnection.getInstance().getConnection();
    }

    @Override
    public long addUserInBlackList(AuthUser authUser) {
        String sql = "insert into black_list(date_block, auth_user_id) values (?, ?)";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ){
            preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
            preparedStatement.setLong(2, authUser.getId());
            preparedStatement.executeUpdate();
            logger.info("black list {} add ", authUser.getUsername());
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                generatedKeys.next();
                return generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("black list {} error add ", authUser.getUsername(), e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteUserOfBlackList(AuthUser authUser) {
        String sql = "delete from black_list where auth_user_id = ?";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setLong(1, authUser.getId());
            preparedStatement.executeUpdate();
            logger.info("black list {} delete ", authUser.getUsername());
        } catch (SQLException e) {
            logger.error("black list {} error delete ", authUser.getUsername(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existUserInBlackList(AuthUser authUser) {
        String sql = "select * from black_list where auth_user_id = ?";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setLong(1, authUser.getId());
            try(ResultSet resultSet = preparedStatement.executeQuery()) {

                if (resultSet.next() && resultSet.getLong("auth_user_id") == authUser.getId()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            logger.error("black list {} error of exist ", authUser.getUsername(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BlackListDTO> getUsersOfBlackList() {
        String sql = "SELECT auth_user.id, username, role, date_block  FROM auth_user " +
                "inner join black_list on auth_user.id = black_list.auth_user_id;";
        List<BlackListDTO> users = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()){
                    users.add(new BlackListDTO(
                            resultSet.getLong("id"),
                            resultSet.getString("username"),
                            resultSet.getDate("date_block"),
                            Role.valueOf(resultSet.getString("role"))
                    ));
                }
                return users;
            }
        } catch (SQLException e) {
            logger.error("black list {} error get List of Users", e);
            throw new RuntimeException(e);
        }
    }
}

