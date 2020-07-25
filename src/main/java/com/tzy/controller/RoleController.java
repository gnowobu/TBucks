package com.tzy.controller;

import com.tzy.model.Customer;
import com.tzy.model.Role;
import com.tzy.service.CustomerService;
import com.tzy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/roles")
public class RoleController {

    @Autowired
    CustomerService customerService;

    @Autowired
    RoleService roleService;

    @RequestMapping(value = "/setRole/{ID}", method = RequestMethod.POST)
    public Customer setRole(@PathVariable("ID") Long id, @RequestParam("role") String role){

        Role r = roleService.getRoleByName(role);
        return customerService.setCustomerRole(id, r);
    }

}
