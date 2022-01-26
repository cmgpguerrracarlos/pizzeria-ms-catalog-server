package com.pizzeria.pizzeria.data;

import com.pizzeria.pizzeria.model.Pizza;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface CatalogGenerator {
    public List<String> generateCatalog();
    String buildUid(String...elements);
    float calculatePrice(String uid);
    public List<Pizza> generateFinalCatalog();
}
