package com.tzy.controller;

import com.tzy.model.Customer;
import com.tzy.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Customer> getCustomers(){

        return customerService.getCustomer();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(){
        logger.debug("test success");
    }

    @RequestMapping(value = "/{Id}", method = RequestMethod.GET)
    public void getCustomerById(@PathVariable(name = "Id") Long id){
        logger.debug("id is: " + id);
    }


}
