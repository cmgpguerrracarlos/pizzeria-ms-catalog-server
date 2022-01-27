package com.pizzeria.pizzeria.controller;


import com.pizzeria.pizzeria.data.CatalogGenerator;
import com.pizzeria.pizzeria.model.Pizza;
import com.pizzeria.pizzeria.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/pizzas/price/{code}")
    public Float getPriceFromCode(@PathVariable("code") String code){
        return pizzaService.getPizzaByCode(code).getPrice();
    }

    /*@PutMapping(value = "/pizzas")
    public ResponseEntity<Pizza> updateCatalog(@RequestBody Pizza pizza){
        try{
            return new ResponseEntity<>(pizzaService.updateCatalog(pizza), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }*/

    @DeleteMapping(value = "/pizzas/{uid}")
    public ResponseEntity<Pizza> deleteOrder(@PathVariable("uid") String uid){
        try{
            pizzaService.deletePizzaByCode(uid);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }
}
