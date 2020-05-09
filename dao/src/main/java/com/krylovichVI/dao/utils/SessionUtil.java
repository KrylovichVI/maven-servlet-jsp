package com.krylovichVI.dao.utils;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;

public class SessionUtil {
    private static SessionFactory instance;

    private static SessionFactory createSessionFactory(){
        Configuration configure = new Configuration()
                .configure("hibernate.cfg.xml");
        return configure.buildSessionFactory();
    }

    public static Session openSession(){
        if(instance == null){
            instance = createSessionFactory();
        }
        return instance.openSession();
    }

    public static EntityManager openEntityManager(){
        return openSession().unwrap(EntityManager.class);
    }

    public static void closeSessionFactory(){
        if(instance != null && !instance.isClosed()){
            instance.close();
        }
    }
}
