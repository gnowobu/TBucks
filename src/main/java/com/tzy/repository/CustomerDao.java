package com.tzy.repository;

import com.tzy.model.Customer;
import com.tzy.model.Order;
import com.tzy.model.Role;


import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomer();

    List<Customer> getCustomerWithOrders();

    Customer save(Customer customer);

    boolean delete(Customer customer);

    Customer getById(Long id);

    Customer getByName(String name);

    Customer getCustomerByOrder(Order order);

    Customer getCustomerByCredentials(String name, String password);

    Long getCustomerID(String name, String email);

    Customer setCustomerRole(Long id, Role role);



}
