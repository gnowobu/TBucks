package com.tzy.service;

import com.tzy.model.Order;
import com.tzy.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    public List<Order> getOrders(){
        return orderDao.getOrders();
    }

    public Order save(Order order){
        return orderDao.save(order);
    }

    public boolean delete(Order order){
        return orderDao.delete(order);
    }

    public List<Order> getOrdersWithCoffee(){
        return orderDao.getOrdersWithCoffee();
    }

    public List<Order> getOrdersWithCustomer(){
        return orderDao.getOrdersWithCustomer();
    }
}
