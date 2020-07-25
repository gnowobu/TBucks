package com.tzy.service;

import com.tzy.model.Customer;
import com.tzy.model.Order;
import com.tzy.model.Role;
import com.tzy.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getCustomer(){
        return customerDao.getCustomer();
    }

    public Customer save(Customer customer){
        return customerDao.save(customer);
    }

    public boolean delete(Customer customer){
        return customerDao.delete(customer);
    }

    public Customer getByName(String name){
        return customerDao.getByName(name);
    }

    public Customer getById(Long id) {
        return customerDao.getById(id);
    }

    public Customer getByOrder(Order order){
        return customerDao.getCustomerByOrder(order);
    }

    public List<Customer> getCustomerWithOrders(){
        return customerDao.getCustomerWithOrders();
    }

    public Customer setCustomerRole(long id, Role role){
        return customerDao.setCustomerRole(id, role);
    }

    public Long getCustomerId(String name, String email){
        return customerDao.getCustomerID(name, email);
    }


    public Customer getCustomerByCredentials(String name, String password){
        return customerDao.getCustomerByCredentials(name, password);

    }
}
