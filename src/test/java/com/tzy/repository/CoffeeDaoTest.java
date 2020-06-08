package com.tzy.repository;

import com.tzy.model.Coffee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CoffeeDaoTest {

    CoffeeDao test;

    @BeforeEach
    public void setUp(){
        test = new CoffeeDaoimpl();
    }

    @After
    public void tearDown(){
        test = null;
    }

    @Test
    public void getCoffeeTest(){

        List<Coffee> coffee = test.getCoffee();
        Assert.assertEquals(coffee.size(), 1);
    }


    
}
