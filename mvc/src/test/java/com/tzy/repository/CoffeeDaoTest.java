package com.tzy.repository;

import com.tzy.ApplicationBootstrap;
import com.tzy.model.Coffee;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= ApplicationBootstrap.class)
public class CoffeeDaoTest {

    @Autowired
    private CoffeeDao coffeeDao;

    private Coffee coffee;

    @Before
    public void setUp(){

        coffee = new Coffee();
        //cof.setId(4l);
        coffee.setPrice(new BigDecimal(12.341));
        coffee.setType("Americano");
        coffeeDao.save(coffee);
    }

    @After
    public void tearDown(){
        coffeeDao.delete(coffee);
        coffeeDao = null;
    }

    @Test
    public void CoffeeDaoTest(){
        coffee = coffeeDao.getCoffee().get(0);
        Assert.assertEquals("Americano", coffee.getType());

    }
}
