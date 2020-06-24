package com.tzy.repository;
import com.tzy.ApplicationBootstrap;
import com.tzy.model.Coffee;
import com.tzy.model.Order;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class OrderDaoTest {

    private Order order;
    private Coffee coffee1, coffee2;

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CoffeeDao coffeeDao;

    @Before
    public void setUp(){

        order = new Order();
        coffee1 = new Coffee();
        coffee2 = new Coffee();

        List<Coffee> coffeeList = new ArrayList<>();
        coffeeList.add(coffee1);
        coffeeList.add(coffee2);

        coffee1.setType("cappucino");
        coffee1.setPrice(BigDecimal.valueOf(15.00));
        coffee2.setType("Americano");
        coffee2.setPrice(BigDecimal.valueOf(18.00));
        coffeeDao.save(coffee1);
        coffeeDao.save(coffee2);

        order.setCoffeeList(coffeeList);
        order.setOrder_time(LocalDateTime.now());
        order.setStatus("1");
        order.setTotal(BigDecimal.valueOf(33));
        orderDao.save(order);
    }

    @After
    public void tearDown(){
        orderDao.delete(order);
        coffeeDao.delete(coffee1);
        coffeeDao.delete(coffee2);
    }

    @Test
    public void orderDaoTest(){
        //Assert.assertEquals(1,orderDao.getOrders().size());
        Assert.assertEquals(orderDao.getOrdersWithCoffee().get(0).getCoffeeList().size(), 2);//left join if using lazy fetch
    }
}
