package com.pizzeria.pizzeria.service;

import com.pizzeria.pizzeria.model.Pizza;
import com.pizzeria.pizzeria.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

    @Mock
    PizzaRepository pizzaRepository;

    @InjectMocks
    PizzaService pizzaService;

    List<Pizza> listMockPizzas;

    @BeforeEach
    void setUp() {
        var Pizza1 = new Pizza("yPM", 278.0F, "yankee", "Mediana", "Peperoni");
        var Pizza2 = new Pizza("yPG", 272.0F, "yankee", "Grande", "Peperoni");
        var Pizza3 = new Pizza("yCC", 255.0F, "yankee", "Chica", "Cebolla");

        listMockPizzas = Arrays.asList(Pizza1, Pizza2, Pizza3);
    }

    @Test
    @DisplayName("Test getPizzaByCode")
    void getPizzaByCode() {
        Mockito.when(pizzaRepository.findById("yPM")).thenReturn(Optional.ofNullable(listMockPizzas.get(0)));

        var result = pizzaService.getPizzaByCode("yPM");

        assertNotNull(result,"Verify object is not null");
        assertEquals("yPM",result.getUid(), "Testing Uid of the Pizza");
        assertEquals("yankee",result.getStyle(), "Testing style of the Pizza");
    }


    @Test
    @DisplayName("Test getAll")
    void getAll() {
        Mockito.when(pizzaRepository.findAll()).thenReturn(listMockPizzas);

        var result = pizzaService.getAll();

        assertEquals(3, result.size(),"Testing size of the result");
        assertEquals("yPM",listMockPizzas.get(0).getUid(), "Testing Uid of the first Pizza");
        assertEquals(272.0F,listMockPizzas.get(1).getPrice(), "Testing price of the second Pizza");

    }

    @Test
    @DisplayName("Test savePizza")
    void savePizza() {
        var pizza = listMockPizzas.get(0);
        Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza);
        Mockito.when(pizzaService.getPizzaByCode("yPM")).thenReturn(pizza);
        var result = pizzaService.savePizza(pizza);
        assertEquals(278.0F,result.getPrice(), "Testing price of the result");
        assertEquals("yankee",result.getStyle(), "Testing style of the result");
    }

    @Test
    void updateCatalog() {
        var pizza = listMockPizzas.get(1);
        Mockito.when(pizzaRepository.save(pizza)).thenReturn(pizza);
        var result = (Pizza)pizzaService.updateCatalog(pizza);
        assertNotNull(result,"Verify object is not null");
        assertEquals(272.0F,result.getPrice(), "Testing price of the result");
        assertEquals("Grande",result.getSize(), "Testing size of the result");

    }

    @Test
    void deletePizza() {
        pizzaService.deletePizzaByCode("yPM");
        verify(pizzaRepository).deleteById("yPM");
    }

}
