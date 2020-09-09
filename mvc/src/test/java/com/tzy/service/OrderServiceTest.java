package com.tzy.service;

import com.tzy.ApplicationBootstrap;
import com.tzy.model.Customer;
import com.tzy.model.Order;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    private Customer customer;
    private Order order1;
    private Order order2;

    @Before
    public void setUp(){
        customer = new Customer();
        order1 = new Order();
        order2 = new Order();

        customer.setName("test");
        customerService.save(customer);

        order1.setCustomer(customer);
        order2.setCustomer(customer);
        orderService.save(order1);
        orderService.save(order2);

    }

    @Test
    public void getOrdersWithCustomerTest(){
        Assert.assertEquals(2,orderService.getOrdersWithCustomer(customer).size());
    }

    @After
    public void tearDown(){
        orderService.delete(order1);
        orderService.delete(order2);
        customerService.delete(customer);
    }


}
