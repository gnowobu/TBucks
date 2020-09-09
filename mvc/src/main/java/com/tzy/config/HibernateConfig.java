package com.tzy.config;

import com.tzy.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory getHibernateSessionFactory(){
        HibernateUtil hibernateUtil = new HibernateUtil();
        return hibernateUtil.getSessionFactory();
    }


}
