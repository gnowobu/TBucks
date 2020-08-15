package com.tzy.controller;

import com.tzy.service.CustomerService;
import com.tzy.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/sign_up")
public class SignUpController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public boolean signUp(@RequestBody Customer customer){

        if(customerService.save(customer) != null){
            logger.info("successfully saved");
            return true;
        }
        return false;
    }





}
