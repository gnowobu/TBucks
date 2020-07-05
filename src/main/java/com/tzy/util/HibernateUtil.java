package com.tzy.util;

import com.github.fluent.hibernate.cfg.scanner.EntityScanner;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Properties;


public class HibernateUtil {
    private SessionFactory sessionFactory; //singleton
    private Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    public SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            String[] modelPackages = {"com.tzy.model"};
            String dbDriver = System.getProperty("database.driver");
            String dbDialect = System.getProperty("database.dialect");
            String dbUrl = System.getProperty("database.url");
            String dbUser = System.getProperty("database.user");
            String dbPassword = System.getProperty("database.password");

            Configuration configuration = new Configuration();
            Properties settings = new Properties();
            settings.put(Environment.DRIVER, dbDriver);
            settings.put(Environment.DIALECT, dbDialect);
            settings.put(Environment.URL, dbUrl);
            settings.put(Environment.USER, dbUser);
            settings.put(Environment.PASS, dbPassword);
            settings.put(Environment.SHOW_SQL, "true");
            settings.put(Environment.HBM2DDL_AUTO, "validate");//verify the annotation
            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
            configuration.setProperties(settings);
            EntityScanner.scanPackages(modelPackages).addTo(configuration);

            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

            ServiceRegistry serviceRegistry = registryBuilder.applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);



        }
        return sessionFactory;
    }

//    public static void main(String[] args){
//
//        SessionFactory test = HibernateUtil.getSessionFactory();
//        logger.info("success " + test.hashCode());
//        Session s = test.openSession();//factory pattern
//        s.close();
//
//    }
}



