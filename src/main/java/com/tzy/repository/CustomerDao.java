package com.tzy.repository;

import com.tzy.model.Customer;


import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomer();

    Customer save(Customer customer);

    boolean delete(Customer customer);

    Customer getByName(String name);

    List<Customer> getCustomerWithOrders();


}
