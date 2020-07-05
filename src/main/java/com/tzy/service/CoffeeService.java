package com.tzy.service;

import com.tzy.model.Coffee;
import com.tzy.repository.CoffeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoffeeService {
    @Autowired
    private CoffeeDao coffeeDao;

    public List<Coffee> getCoffee(){
        return coffeeDao.getCoffee();
    };

    public Coffee save(Coffee coffee){
        return coffeeDao.save(coffee);
    };

    public boolean delete(Coffee coffee) {
        return coffeeDao.delete(coffee);
    }

    Coffee getById(Long id){
        return coffeeDao.getById(id);
    };


}
