package com.krylovichVI.dao.config;

import org.springframework.beans.factory.annotation.Value;

public class DataSourceSettings {
    @Value("${url}")
    private String url;

    @Value("${dbusername}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${driver}")
    private String driver;

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriver() {
        return driver;
    }
}
