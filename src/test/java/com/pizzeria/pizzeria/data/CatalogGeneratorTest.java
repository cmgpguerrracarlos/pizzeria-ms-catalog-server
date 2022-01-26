package com.pizzeria.pizzeria.data;

import com.pizzeria.pizzeria.model.Pizza;
import com.pizzeria.pizzeria.repository.PizzaRepository;
import com.pizzeria.pizzeria.service.PizzaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CatalogGeneratorTest {

    @Mock
    PizzaRepository pizzaRepository;

    @InjectMocks
    CatalogGeneratorImpl catalogGenerator;


    List<Pizza> listMockPizzas;

    @BeforeEach
    void setUp() {
        var Pizza1 = new Pizza("yPM", 278.0F, "yankee", "Mediana", "Peperoni");
        var Pizza2 = new Pizza("yPG", 272.0F, "yankee", "Grande", "Peperoni");
        var Pizza3 = new Pizza("yCC", 255.0F, "yankee", "Chica", "Cebolla");

        listMockPizzas = Arrays.asList(Pizza1, Pizza2, Pizza3);
    }

    @Test
    @DisplayName("Test generateFinalCatalog")
    void generateFinalCatalog() throws Exception {
        Mockito.when(pizzaRepository.saveAll(listMockPizzas)).thenReturn(listMockPizzas);
        assertThat(catalogGenerator.generateFinalCatalog().size(), is(24));

    }



}
