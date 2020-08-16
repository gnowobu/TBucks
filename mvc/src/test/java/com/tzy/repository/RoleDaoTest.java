package com.tzy.repository;


import com.tzy.ApplicationBootstrap;
import com.tzy.model.Customer;
import com.tzy.model.Role;
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
public class RoleDaoTest {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private CustomerDao customerDao;

    private Customer customer;
    private Role role1, role2;

    @Before
    public void setUp() {
        role1 = new Role();
        role2 = new Role();
        customer = new Customer();
        customer.setName("test");
        customer.setPassword("test");
        customer.setEmail("test");
        customerDao.save(customer);

        role1.setName("role1_manager");
        role1.setAllowedCreate(true);
        role1.setAllowedDelete(true);
        role1.setAllowedRead(true);
        role1.setAllowedResource("yes");
        role1.setAllowedUpdate(true);
        roleDao.save(role1);

        role2.setName("role2_employee");
        role2.setAllowedCreate(false);
        role2.setAllowedDelete(false);
        role2.setAllowedRead(true);
        role2.setAllowedResource("no");
        role2.setAllowedUpdate(false);
        roleDao.save(role2);

        customer.addRole(role1);
        customer.addRole(role2);

        customerDao.save(customer);

    }

    @After
    public void tearDown() {
        roleDao.delete(role1);
        roleDao.delete(role2);
        customerDao.delete(customer);
    }

    @Test
    public void getRoleCustomerTest(){

        Customer customer = customerDao.getByName("test");
        Assert.assertEquals(customer.getRoles().size(), 2);

    }

}
