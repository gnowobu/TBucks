package com.tzy.controller;

import com.tzy.model.Customer;
import com.tzy.model.Order;
import com.tzy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List<Order> getOrders(){
        return orderService.getOrders();
    }

    @RequestMapping(value = "/withCustomer", method = RequestMethod.GET)
    public List<Order> getOrdersWithCustomer(@RequestBody Customer customer){

        List<Order> list = orderService.getOrdersWithCustomer(customer);

        return list;

    }

}
