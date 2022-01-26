package com.pizzeria.pizzeria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzeria.pizzeria.model.Pizza;
import com.pizzeria.pizzeria.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = PizzaController.class)
@ExtendWith(SpringExtension.class)
class PizzaControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    String baseUrl = "/pizzas";

    @MockBean
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
    @DisplayName("Test getAll")
    void testGetAll() throws Exception {

        when(pizzaService.getAll()).thenReturn(listMockPizzas);

        mockMvc.perform(get(baseUrl).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$[2].uid").value("yPG"))
                .andExpect(jsonPath("$[2].style").value("yankee"));

    }

    @Test
    @DisplayName("Test getPizzasByCode")
    void testGetThePizzas() throws Exception {
        given(pizzaService.getPizzaByCode("yPG")).willReturn(listMockPizzas.get(1));
        mockMvc.perform(get(baseUrl + "/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.uid").value("yPG"))
                .andExpect(jsonPath("$.style").value("yankee"));
    }

    @Test
    @DisplayName("Test createPizza")
    void testCreatePizza() throws Exception {
        var Pizza = listMockPizzas.get(0);
        given(pizzaService.savePizza(Pizza)).willReturn(Pizza);
        String orderJsonString = this.mapper.writeValueAsString(Pizza);
        mockMvc.perform(post(baseUrl + "/").contentType(MediaType.APPLICATION_JSON).content(orderJsonString))
                .andExpect(status().is2xxSuccessful());

    }
}


