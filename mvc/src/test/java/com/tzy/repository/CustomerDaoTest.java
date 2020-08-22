package com.tzy.repository;
import com.tzy.ApplicationBootstrap;
import com.tzy.model.Customer;
import com.tzy.model.Order;
import com.tzy.model.Role;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private RoleDao roleDao;

    private Customer customer;
    private Order order1, order2;
    private Role role;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setUp() {
        order1 = new Order();
        order2 = new Order();
        customer = new Customer();
        customer.setName("test");
        customer.setPassword("test");
        customer.setEmail("test");
        customerDao.save(customer);
        order1.setCustomer(customer);
        order2.setCustomer(customer);
        orderDao.save(order1);
        orderDao.save(order2);

        role = new Role();
        role.setName("role1_manager");
        role.setAllowedCreate(true);
        role.setAllowedDelete(true);
        role.setAllowedRead(true);
        role.setAllowedResource("yes");
        role.setAllowedUpdate(true);
        roleDao.save(role);

    }

    @After
    public void tearDown() {
        orderDao.delete(order1);
        orderDao.delete(order2);
        customerDao.delete(customer);
        roleDao.delete(role);
    }


    @Test
    public void getCustomerByOrderTest(){

        Assert.assertEquals(customerDao.getCustomerByOrder(order1).getName(), "test");

    }

    @Test
    public void getCustomerIDTest(){

        long id = customerDao.getCustomerID("test","test");
        System.out.println("id is: " + id);
    }

    @Test
    public void addRoleTest(){

        Role role = roleDao.getRoleByName("role1_manager");
        long id = customer.getId();
        customer = customerDao.setCustomerRole(id,role);
        Assert.assertEquals(customerDao.getById(id).getRoles().size(),1);

    }

}
