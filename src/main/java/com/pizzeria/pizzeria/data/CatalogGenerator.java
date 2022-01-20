package com.pizzeria.pizzeria.data;

import com.pizzeria.pizzeria.model.Pizza;

import java.util.List;

public interface CatalogGenerator {
    public List<String> generateCatalog();
    String buildUid(String...elements);
    float calculatePrice(String uid);
    public List<Pizza> generateFinalCatalog();
}
