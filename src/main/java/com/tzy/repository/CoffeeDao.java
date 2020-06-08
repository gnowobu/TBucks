package com.tzy.repository;

import com.tzy.model.Coffee;

import java.util.List;

public interface CoffeeDao {

    List<Coffee> getCoffee();

    Coffee save(Coffee coffee);

    boolean delete(Coffee coffee);

    Coffee getBy(Long id);
}
