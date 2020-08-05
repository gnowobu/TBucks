package com.tzy.repository;
import com.tzy.model.Coffee;
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

@Repository
public class RoleDaoImp implements RoleDao {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Role> findAllRoles() {
        String hql = "FROM Role";
        Session s = sessionFactory.openSession();
        List<Role> res = new ArrayList<>();
        Transaction transaction = null;

        try{
            transaction = s.beginTransaction();
            Query<Role> query = s.createQuery(hql);
            res = query.list();
            transaction.commit();
        } catch (HibernateException e){
            //logger.error("exception happened");
            transaction.rollback();
        } finally {
            s.close();
        }
        return res;
    }

    @Override
    public Role getRoleByName(String name) {
        Session s = sessionFactory.openSession();
        String hql = "FROM Role where name = :name ";
        Role role = null;

        try{

            Query<Role> query = s.createQuery(hql);
            query.setParameter("name",name);
            role = query.uniqueResult();

        } catch (HibernateException e){
            logger.error("exception happened");

        } finally {
            s.close();
        }

        return role;
    }

    @Override
    public void save(Role role) {

        Transaction transaction = null;
        Session s = sessionFactory.openSession();

        try {
            transaction = s.beginTransaction();
            s.saveOrUpdate(role);
            transaction.commit();
            s.close();

        } catch (Exception e){
            if(transaction != null) transaction.rollback();
            logger.error("failure to insert record", e);
            s.close();
        }

    }

    @Override
    public boolean delete(Role role) {
        String hql = "DELETE Role as r where r.id = :Id";//":Id" displays the id, "Id" is just a variable name.
        int deletedCount = 0;
        Transaction transaction = null;
        Session s = sessionFactory.openSession();

        try{
            transaction = s.beginTransaction();
            Query<Coffee> query = s.createQuery(hql);
            query.setParameter("Id", role.getId());
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
}
