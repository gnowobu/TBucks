package com.tzy.repository;

import com.tzy.model.Coffee;
import com.tzy.model.Customer;
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
public class CustomerDaoImp implements CustomerDao{
    private Logger logger = LoggerFactory.getLogger(getClass());

    public List<Customer> getCustomer(){

        String hql = "FROM Customer";
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();

        List<Customer> res = new ArrayList<>();
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

    public Customer save(Customer customer){

        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();

        try {
            transaction = s.beginTransaction();
            s.save(customer);
            transaction.commit();
            s.close();
            return customer;
        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error("failure to insert record", e);
            s.close();
            return null;
        }

    }

    public boolean delete(Customer customer){
        String hql = "DELETE Customer as c where c.id = :Id";//":Id" displays the id, "Id" is just a variable name.
        int deletedCount = 0;
        Transaction transaction = null;
        Session s = HibernateUtil.getSessionFactory().openSession();

        try{
            transaction = s.beginTransaction();
            Query<Coffee> query = s.createQuery(hql);
            query.setParameter("Id",customer.getId());
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

    public Customer getByName(String name){

        String hql = "SELECT FROM Customer where name = :name";
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();
        Customer customer = new Customer();

        try {

            Query query = s.createQuery(hql);
            query.setParameter("name", name);
            customer = (Customer) query.list().get(0);//name column is not-null
            s.close();

        } catch (HibernateException e){
            logger.error("session close exception，try again");
            s.close();
        }
        return customer;
    }

    public List<Customer> getCustomerWithOrders(){

        String hql = "FROM Customer as c left join fetch c.orders";
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session s = sessionFactory.openSession();

        List<Customer> res = new ArrayList<>();
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
}
