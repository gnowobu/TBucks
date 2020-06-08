package com.tzy.repository;

import com.tzy.jdbc.CustomerDAOjdbc;
import com.tzy.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerDaoTest {

    CustomerDAOjdbc dao;
    @BeforeEach
    void setUp() {
        dao = new CustomerDAOjdbc();
    }

    @AfterEach
    void tearDown() {
        dao = null;
    }


    @Test
    public void getCustomerTest(){

        assertEquals(2, dao.getCustomers().size());

    }


    @Test
    public void createCustomerTest(){

        Customer customer = new Customer();
        customer.setId(2);
        customer.setEmail("test@test.com");
        customer.setPassword("123");
        customer.setName("Test");

        dao.createCustomer(customer);

        Customer customer1 = dao.findById(1l);

        assertEquals(1, customer1.getId());

    }
}
