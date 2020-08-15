package com.tzy.controller;

import com.tzy.service.CustomerService;
import com.tzy.service.JWTService;
import com.tzy.model.Customer;
import com.tzy.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**1.validate user exists in db
2.create JWToken
3.return token*/

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired private JWTService jwtService;

    @Autowired private CustomerService customerService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String authentication(@RequestBody Customer customer){

        try{
            logger.debug("customer name: " + customer.getName());
            Customer c = customerService.getCustomerByCredentials(customer.getName(), customer.getPassword());
            String token = jwtService.generateToken(c);
            return token;

        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/msg", method = RequestMethod.POST)
    public void sendMessageTo(@RequestParam(name = "phone") String phoneNumber){
        messageService.sendMessage(phoneNumber,1);
    }



}
