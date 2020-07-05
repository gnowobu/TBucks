package com.tzy.repository;
import com.tzy.ApplicationBootstrap;
import com.tzy.model.Customer;
import com.tzy.model.Order;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private OrderDao orderDao;

    private Customer customer;
    private Order order1, order2;

    @Before
    public void setUp() {
        order1 = new Order();
        order2 = new Order();
        customer = new Customer();
        customer.setName("test");
        customer.setPassword("test");
        customer.setEmail("test");
        order1.setCustomer(customer);
        order2.setCustomer(customer);
    }

    @After
    public void tearDown() {
        orderDao.delete(order1);
        orderDao.delete(order2);
        customerDao.delete(customer);
    }


    @Test
    public void getCustomerTest(){

        Assert.assertEquals(customerDao.getCustomerWithOrders().size(), 2);

    }

}
