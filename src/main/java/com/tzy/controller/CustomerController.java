package com.tzy.controller;
import com.tzy.model.Customer;
import com.tzy.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public List<Customer> getCustomers(){
        return customerService.getCustomer();
    }

//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public void test(){
//        logger.debug("test success");
//    }


    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public Customer getCustomerByName(@PathVariable(name = "Id") long id){
        logger.debug("the id is: " + id);
        return customerService.getById(id);
    }

    @RequestMapping(value = "/update_name/{Id}", method = RequestMethod.PATCH) //PATCH: to update a single attribute.(request parameter)
    public Customer updateCustomerName(@PathVariable("Id") Long id, @RequestParam("name") String name){

        Customer c = customerService.getById(id);
        c.setName(name);
        customerService.save(c);
        return c;
    }

    @RequestMapping(value = "/update_email/{Id}")
    public Customer updateCustomerEmail(@PathVariable("Id") Long id, @RequestParam("email") String email){

        Customer c = customerService.getById(id);
        c.setEmail(email);
        customerService.save(c);
        return c;

    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)//test data input
    public void create(@RequestBody Customer customer){
        customerService.save(customer);
    }


    @RequestMapping(value="/{Id}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable(name = "Id") Long id){ //delete by id
        Customer customer = customerService.getById(id);
        return customerService.delete(customer);

    }
}
