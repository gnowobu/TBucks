package com.tzy.repository;

import com.tzy.model.Coffee;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

public class CoffeeDaoTest {

    CoffeeDao test;
    private Coffee cof;

    @BeforeEach
    public void setUp(){
        test = new CoffeeDaoimpl();

        cof = new Coffee();
        cof.setId(4l);
        cof.setPrice(new BigDecimal(12.341));
        cof.setType("Americano");
        test.save(cof);

    }

    @AfterEach
    public void tearDown(){
        test.delete(cof);
        test = null;

    }

    @Test
    public void CoffeeDaoTest(){
        test.getBy(4l);
        Assert.assertEquals("Americano", cof.getType());

    }


    
}
