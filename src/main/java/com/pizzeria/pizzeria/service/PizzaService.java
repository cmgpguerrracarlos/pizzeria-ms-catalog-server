package com.pizzeria.pizzeria.service;


import com.pizzeria.pizzeria.model.Pizza;
import com.pizzeria.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;


  public List<Pizza> getAll() {
        System.out.println(pizzaRepository.findAll());
        return pizzaRepository.findAll();
    }

    public Pizza getPizzaByCode(String code) {
        return pizzaRepository.findById(code).get();
    }

    public Pizza savePizza(Pizza pizza) {
        //pizzaRepository.saveAll(pizza.toString());
        return pizzaRepository.save(pizza);
    }
}
