package service;

import com.tzy.ApplicationBootstrap;
import com.tzy.model.Customer;
import com.tzy.service.JWTService;
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

    private Customer customer;

    @Before
    public void setUp(){

        customer = new Customer();
        customer.setName("Test");
        customer.setId(2L);


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
