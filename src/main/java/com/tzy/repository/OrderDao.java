package com.tzy.repository;


import com.tzy.model.Order;

import java.util.List;

public interface OrderDao {
    List<Order> getOrders();

    Order save(Order order);

    boolean delete(Order order);

    List<Order> getOrdersWithCoffee();

    List<Order> getOrdersWithCustomer();
}
