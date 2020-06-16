package com.tzy.repository;

import com.tzy.model.Coffee;
import com.tzy.model.Order;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDaoTest {

    private Order order;
    private Coffee coffee1, coffee2;
    private OrderDao orderDao;
    private CoffeeDao coffeeDao;

    @BeforeEach
    public void setUp(){
        orderDao = new OrderDaoImp();
        coffeeDao = new CoffeeDaoimpl();

        order = new Order();
        coffee1 = new Coffee();
        coffee2 = new Coffee();

        coffee1.setType("cappucino");
        coffee1.setPrice(BigDecimal.valueOf(15.00));
        coffee2.setType("Americano");
        coffee2.setPrice(BigDecimal.valueOf(18.00));

        coffeeDao.save(coffee1);
        coffeeDao.save(coffee2);
        order.addCoffee(coffee1);
        order.addCoffee(coffee2);
        order.setOrder_time(LocalDateTime.now());
        order.setStatus("1");
        order.setTotal(BigDecimal.valueOf(33));
        orderDao.save(order);
    }

    @AfterEach
    public void tearDown(){
        orderDao.delete(order);
        coffeeDao.delete(coffee1);
        coffeeDao.delete(coffee2);

    }

    @Test
    public void orderDaoTest(){

        Assert.assertEquals(orderDao.getOrders().get(0).getCoffeeList().size(), 2);

    }
}
