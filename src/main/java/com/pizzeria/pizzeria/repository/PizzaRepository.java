package com.pizzeria.pizzeria.repository;

import com.pizzeria.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface PizzaRepository extends JpaRepository<Pizza, String>{

}
