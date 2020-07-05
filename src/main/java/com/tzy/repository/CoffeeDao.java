package com.tzy.repository;

import com.tzy.model.Coffee;

import java.util.List;

public interface CoffeeDao {

    List<Coffee> getCoffee();

    Coffee save(Coffee coffee); // save or update

    boolean delete(Coffee coffee);

    Coffee getById(Long id);

}
