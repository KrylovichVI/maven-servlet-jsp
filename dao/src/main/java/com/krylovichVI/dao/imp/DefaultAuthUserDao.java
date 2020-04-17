package com.krylovichVI.dao.imp;

import com.krylovichVI.dao.AuthUserDao;
import com.krylovichVI.dao.JDBCConnection;
import com.krylovichVI.pojo.AuthUser;
import com.krylovichVI.pojo.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultAuthUserDao implements AuthUserDao {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAuthUserDao.class);
    private static AuthUserDao instance;


    private DefaultAuthUserDao() {}

    public static AuthUserDao getInstance(){
        if(instance == null){
            instance = new DefaultAuthUserDao();
        }
        return instance;
    }

    @Override
    public AuthUser getByLogin(String login) {
        String sql = "select * from auth_user where username = ?";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setString(1, login);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return new AuthUser(
                            resultSet.getLong("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            Role.valueOf(resultSet.getString("role"))
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("auth_user {} error by login ", login, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public long saveAuthUser(AuthUser authUser) {
        final String sql = "insert into auth_user (username, password, role)  values(?,?,?)";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            preparedStatement.setString(1, authUser.getUsername());
            preparedStatement.setString(2, authUser.getPassword());
            preparedStatement.setString(3, authUser.getRole().name());
            preparedStatement.executeUpdate();
            logger.info("auth_user {} save user", authUser.getUsername(), authUser.getPassword(), authUser.getRole().name());
            try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                generatedKeys.next();
                return generatedKeys.getLong(1);
            }

        } catch (SQLException e) {
            logger.error("auth_user {} error by save Auth_User ", authUser.getUsername(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AuthUser> getUsers() {
        List<AuthUser> listOfAuthUser = new ArrayList<>();
        String sql = "select * from auth_user";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                listOfAuthUser.add(new AuthUser(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role"))));
            }
        } catch (SQLException e) {
            logger.error("auth_user {} error getUsers ", e);
            throw new RuntimeException(e);
        }
        return listOfAuthUser;
    }

    @Override
    public AuthUser getById(Long id) {
        String sql = "select * from auth_user where id = ?";
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                if(resultSet.next()){
                    return new AuthUser(
                            resultSet.getLong("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            Role.valueOf(resultSet.getString("role"))
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            logger.error("auth_user {} error by Id ", id, e);
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        return JDBCConnection.getInstance().getConnection();
    }
}
