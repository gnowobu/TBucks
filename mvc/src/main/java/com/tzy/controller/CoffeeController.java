package com.tzy.controller;

import com.tzy.model.Coffee;
import com.tzy.service.CoffeeService;
import com.tzy.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/coffee")
public class CoffeeController {

    @Autowired
    CoffeeService coffeeService;



    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<Coffee> getCoffeeList(){

        List<Coffee> coffeeList = new ArrayList<>();

        return coffeeService.getCoffee();
    }



}
