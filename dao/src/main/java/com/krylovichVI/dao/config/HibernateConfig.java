package com.krylovichVI.dao.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@Import(SettingsConfig.class)
public class HibernateConfig {
    private final static int MAX_POOL_SIZE = 20;

    private final SettingsConfig settingsConfig;

    public HibernateConfig(SettingsConfig settingsConfig) {
        this.settingsConfig = settingsConfig;
    }

    @Bean
    public DataSource dataSource(){
        DataSourceSettings dataSourceSettings = settingsConfig.dataSourceSettings();

        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(dataSourceSettings.getUrl());
        hikariDataSource.setUsername(dataSourceSettings.getUsername());
        hikariDataSource.setPassword(dataSourceSettings.getPassword());
        hikariDataSource.setDriverClassName(dataSourceSettings.getDriver());
        hikariDataSource.setMaximumPoolSize(MAX_POOL_SIZE);

        return hikariDataSource;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(){
        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan("com.krylovichVI.pojo");
        localSessionFactoryBean.setHibernateProperties(settingsConfig.hibernateProperties());

        return localSessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactoryBean().getObject());
        return transactionManager;
    }

}
