package com.tzy.repository;

import com.tzy.model.Coffee;
import com.tzy.model.Customer;
import com.tzy.model.Order;

import com.tzy.model.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class CustomerDaoImp implements CustomerDao{
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    public List<Customer> getCustomer(){

        String hql = "FROM Customer";
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
        Session s = sessionFactory.openSession();

        try {
            transaction = s.beginTransaction();
            s.saveOrUpdate(customer);
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
        Session s = sessionFactory.openSession();

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

        String hql = "FROM Customer as c left join fetch c.orders where c.name = :name ";
        Session s = sessionFactory.openSession();
        Customer customer = new Customer();

        try {

            Query query = s.createQuery(hql);
            query.setParameter("name", name);
            customer = (Customer) query.uniqueResult();//name column is not-null
            s.close();

        } catch (HibernateException e){
            logger.error("session close exception，try again");
            s.close();
        }
        return customer;
    }

    public List<Customer> getCustomerWithOrders(){

        String hql = "FROM Customer as c left join fetch c.orders";
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

    @Override
    public Customer getCustomerByOrder(Order order) {

        String hql = "FROM Customer where id =: id";
        Session s = sessionFactory.openSession();
        Customer customer = new Customer();

        try {

            Query query = s.createQuery(hql);
            query.setParameter("id", order.getCustomer().getId());
            customer = (Customer) query.uniqueResult();//name column is not-null
            s.close();

        } catch (HibernateException e){
            logger.error("session close exception，try again");
            s.close();
        }
        return customer;
    }

    @Override
    public Customer getCustomerByCredentials(String name, String password) {
        String hql = "from Customer c where lower(c.name)= :name and c.password = :password";

        Session s = sessionFactory.openSession();
        Customer customer = null;
        //Transaction transaction = null;
        try {
//            transaction = s.beginTransaction();
            Query<Customer> query = s.createQuery(hql);
            query.setParameter("name",name.toLowerCase().trim());
            query.setParameter("password",password);
            customer = query.uniqueResult();
            s.close();
            //transaction.commit();
        } catch (HibernateException e) {
            //transaction.rollback();
            logger.error("session exception, try again");
        }

        return customer;
    }

    @Override
    public Long getCustomerID(String name, String email) {


        String hql = "from Customer c where lower(c.name)= :name and c.email = :email";

        Session s = sessionFactory.openSession();
        Customer customer = null;

        try {
            Query<Customer> query = s.createQuery(hql);
            query.setParameter("name",name.toLowerCase().trim());
            query.setParameter("email",email);
            customer = query.uniqueResult();
            s.close();
        } catch (HibernateException e) {
            logger.error("session exception, try again");
            s.close();
        }
        return customer.getId();
    }

    @Override
    public Customer setCustomerRole(Long id, Role role) {

        String hql = "from Customer c where c.id = :id";
        Set<Role> roleSet;
        Customer customer = null;
        Session s = sessionFactory.openSession();
        Transaction transaction = null;
        try {

            transaction = s.beginTransaction(); //here the transaction can't be omitted. Otherwise Hibernate can't recognize the objects from db query.
            Query<Customer> query = s.createQuery(hql);
            query.setParameter("id",id);

            customer = query.uniqueResult();
            roleSet = customer.getRoles();
            roleSet.add(role);
            customer.setRoles(roleSet);

            s.save(customer); //both save() or saveOrUpdate() work.
            transaction.commit();
        } catch (HibernateException e) {
            logger.error("session exception, try again");
            transaction.rollback();
        } finally {
            s.close();
        }
        return customer;
    }

    public Customer getById(Long id){

        String hql = "FROM Customer where id =: id";
        Session s = sessionFactory.openSession();
        Customer customer = new Customer();

        try {

            Query query = s.createQuery(hql);
            query.setParameter("id", id);
            customer = (Customer) query.uniqueResult();//name column is not-null
            s.close();

        } catch (HibernateException e){
            logger.error("session close exception，try again");
            s.close();
        }
        return customer;
    }


}
