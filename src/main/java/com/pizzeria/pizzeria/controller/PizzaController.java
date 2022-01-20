package com.pizzeria.pizzeria.controller;


import com.pizzeria.pizzeria.data.CatalogGenerator;
import com.pizzeria.pizzeria.model.Pizza;
import com.pizzeria.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CatalogGenerator catalogGenerator;

    @GetMapping("/pizzas")
    public List<Pizza> getAll(){
        return pizzaService.getAll();
    }

    @GetMapping("/pizzas/{uid}")
    public Pizza getThePizzas(@PathVariable("uid") String code){
        return pizzaService.getPizzaByCode(code);
    }

    @PostMapping("/pizzas")
    public Pizza createPizza(@RequestBody Pizza pizza){
        return pizzaService.savePizza(pizza);
    }

    @GetMapping("/pizzas/start")
    public List<Pizza> createRandomCatalog(){
        return catalogGenerator.generateFinalCatalog();
    }
}
