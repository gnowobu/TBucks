package com.tzy.service;

import com.tzy.model.Customer;
import com.tzy.model.Order;
import com.tzy.repository.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<Order> getOrdersWithCustomer(Customer customer){

        List<Order> orderList = orderDao.getOrdersWithCustomer();
        List<Order> res = new ArrayList<>();
        for(Order order: orderList){
            if(order.getCustomer() != null && order.getCustomer().equals(customer)) res.add(order);
        }
        return res;
    }
}
