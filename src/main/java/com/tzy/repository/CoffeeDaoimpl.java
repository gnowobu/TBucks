package com.tzy.repository;

import com.tzy.model.Coffee;
import com.tzy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;

@Repository
public class CoffeeDaoimpl implements CoffeeDao {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public List<Coffee> getCoffee() {
        String hql = "FROM Coffee";
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();

        List<Coffee> res = new ArrayList<>();
        try {
            Query query = s.createQuery(hql);
            res = query.list();
            s.close();

        } catch (HibernateException e){
            logger.error("session close exception，try again");
            s.close();
        }
        return res;
    }

    @Override
    public Coffee save(Coffee coffee) {

        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = s.beginTransaction();
            s.save(coffee);
            transaction.commit();
            s.close();
            return coffee;
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error("failure to insert record", e);
            s.close();
            return null;
        }

    }

    @Override
    public boolean delete(Coffee coffee) {

        String hql = "DELETE Coffee as cof where cof.id = :Id";//":Id" displays the id, "Id" is just a variable name.
        int deletedCount = 0;
        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = s.beginTransaction();
            Query<Coffee> query = s.createQuery(hql);
            query.setParameter("Id",coffee.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            s.close();
            return deletedCount >= 1 ? true:false;
        } catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();

            s.close();
            logger.error("unable to delete a record ", e);
        }

        return false;
    }

    @Override
    public Coffee getBy(Long id) {
        String hql = "FROM Coffee cof where cof.id = :Id";
        List<Coffee> res =  new ArrayList<>();

        Session s =  HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = s.beginTransaction();
            Query<Coffee> query = s.createQuery(hql);
            query.setParameter("Id",id);
            res = query.list();
            transaction.commit();

        } catch (HibernateException e){
            transaction.rollback();
            logger.error("transaction terminated");
        } finally {
            s.close();
        }
        return res.get(0);
    }
}
