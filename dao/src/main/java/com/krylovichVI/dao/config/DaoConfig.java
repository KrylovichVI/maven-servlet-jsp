package com.krylovichVI.dao.config;

import com.krylovichVI.dao.*;
import com.krylovichVI.dao.imp.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import({HibernateConfig.class})
@EnableTransactionManagement
public class DaoConfig {

    private final SessionFactory sessionFactory;

    public DaoConfig(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Bean
    public AuthUserDao authUserDao(){ return new DefaultAuthUserDao(sessionFactory);
    }

    @Bean
    public BlackListDao blackListDao() { return new DefaultBlackListDao(sessionFactory);
    }

    @Bean
    public BookDao bookDao() { return new DefaultBookDao(sessionFactory);
    }

    @Bean
    public OrderDao orderDao() { return new DefaultOrderDao(sessionFactory);
    }

    @Bean
    public UserDao userDao() { return new DefaultUserDao(sessionFactory);
    }

}
