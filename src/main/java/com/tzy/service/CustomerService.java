package com.tzy.service;

import com.tzy.model.Customer;
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

    public List<Customer> getCustomerWithOrders(){
        return customerDao.getCustomerWithOrders();
    }
}
