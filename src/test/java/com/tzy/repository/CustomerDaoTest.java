package com.tzy.repository;
import com.tzy.ApplicationBootstrap;
import com.tzy.model.Customer;
import com.tzy.model.Order;
import com.tzy.model.Role;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
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

    @Autowired
    private RoleDao roleDao;

    private Customer customer;
    private Order order1, order2;
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

    }

    @After
    public void tearDown() {
        orderDao.delete(order1);
        orderDao.delete(order2);
        customerDao.delete(customer);
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

        Role role = roleDao.getRoleByName("Manager");
        long id = customer.getId();
        customer = customerDao.setCustomerRole(id,role);
        Assert.assertEquals(customerDao.getById(id).getRoles().size(),1);

    }

//    @Test //test method written by ryo. DAO
//    public void addRoleTest(){
////        customer customer = new customer();
//        Set<Role> roles = new HashSet<>();
//        customer.setRoles(roles);
//        customer.setName("jfang");
//
//        customer.setEmail("jfang@ascending.com");
//        customer.setPassword("jfang123!@#$");
//        customerDao.save(customer);
//        assertTrue(customer.getId()>0);
//        Customer result = customerDao.getById(customer.getId());
//        Assert.assertEquals(result.getRoles().size(),0);
//        roles.add(roleDao.getRoleByName("Admin"));
//        customer.setRoles(roles);
//        customerDao.save(customer);
//        result = customerDao.getById(customer.getId());
//        Assert.assertEquals(result.getRoles().size(),1);
//    }
}
