package com.tzy.repository;

import com.tzy.model.Coffee;
import com.tzy.model.Order;
import com.tzy.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImp implements OrderDao {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<Order> getOrders() {

        String hql = "FROM Order";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Order> res = new ArrayList<>();
        try{
            transaction = s.beginTransaction();
            Query<Order> query = s.createQuery(hql);
            res = query.list();
            transaction.commit();
        } catch (HibernateException e){
            logger.error("Exception happened");
            transaction.rollback();
        } finally {
            s.close();
        }
        return res;
    }

    @Override
    public Order save(Order order) {

        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = s.beginTransaction();
            s.save(order);
            transaction.commit();
            s.close();
            return order;
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error("failure to insert record", e);
            s.close();
            return null;
        }

    }

    @Override
    public boolean delete(Order order) {
        String hql = "DELETE Order as order where order.id = :Id";//":Id" displays the id, "Id" is just a variable name.
        int deletedCount = 0;
        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = s.beginTransaction();
            Query<Coffee> query = s.createQuery(hql);
            query.setParameter("Id",order.getId());
            deletedCount = query.executeUpdate();
            transaction.commit();
            s.close();
            return deletedCount >= 1;
        } catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();

            s.close();
            logger.error("unable to delete a record ", e);
        }

        return false;
    }

    @Override
    public List<Order> getOrdersWithCoffee() {
        String hql = "FROM Order as o left join fetch o.coffeeList";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Order> res = new ArrayList<>();
        try{
            transaction = s.beginTransaction();
            Query<Order> query = s.createQuery(hql);
            res = query.list();
            transaction.commit();
        } catch (HibernateException e){
            logger.error("Exception happened");
            transaction.rollback();
        } finally {
            s.close();
        }
        return res;
    }

    @Override
    public List<Order> getOrdersWithCustomer() {
        String hql = "FROM Order as o left join fetch o.customer";
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Order> res = new ArrayList<>();
        try{
            transaction = s.beginTransaction();
            Query<Order> query = s.createQuery(hql);
            res = query.list();
            transaction.commit();
        } catch (HibernateException e){
            logger.error("Exception happened");
            transaction.rollback();
        } finally {
            s.close();
        }
        return res;
    }
}
