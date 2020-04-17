package com.krylovichVI.dao;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class JDBCConnection {
    private static JDBCConnection instance;

    private final ComboPooledDataSource pool;

    private JDBCConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        pool = new ComboPooledDataSource();
        ResourceBundle resource = ResourceBundle.getBundle("db");

        String url = resource.getString("url");
        String username = resource.getString("username");
        String password = resource.getString("password");

        pool.setJdbcUrl(url);
        pool.setUser(username);
        pool.setPassword(password);

        pool.setMinPoolSize(5);
        pool.setAcquireIncrement(5);
        pool.setMaxPoolSize(10);
        pool.setMaxStatements(180);
    }

    public static JDBCConnection getInstance(){
        if(instance == null){
            instance = new JDBCConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        try {
            return this.pool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
