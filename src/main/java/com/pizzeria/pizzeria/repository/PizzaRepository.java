package com.pizzeria.pizzeria.repository;

import com.pizzeria.pizzeria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, String>{

}
