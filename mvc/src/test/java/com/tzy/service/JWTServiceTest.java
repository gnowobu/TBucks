package com.tzy.service;

import com.tzy.ApplicationBootstrap;
import com.tzy.model.Customer;
import com.tzy.model.Role;
import com.tzy.repository.RoleDao;
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
public class JWTServiceTest {

    @Autowired
    private JWTService jwtService;

    @Autowired
    RoleDao roleDao;
    private Customer customer;
    private Role role;

    @Before
    public void setUp(){

        role = new Role();
        role.setName("Manager");
        role.setAllowedCreate(true);
        role.setAllowedDelete(true);
        role.setAllowedRead(true);
        role.setAllowedResource("yes");
        role.setAllowedUpdate(true);


        customer = new Customer();
        customer.setName("Test");
        customer.setId(2L);
        customer.addRole(role);




    }

    @After
    public void tearDown(){

    }

    @Test
    public void test(){

        String token = jwtService.generateToken(customer);
        System.out.println(token+ "\n");

        String[] res = token.split("\\.");
        Assert.assertEquals(res.length, 3);

    }
}
