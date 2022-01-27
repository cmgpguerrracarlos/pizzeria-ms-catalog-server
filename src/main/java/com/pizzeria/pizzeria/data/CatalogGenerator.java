package com.pizzeria.pizzeria.data;

import com.pizzeria.pizzeria.model.Pizza;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CatalogGenerator {
    public List<String> generateCatalog();
    String buildUid(String...elements);
    float calculatePrice(String uid);
    public List<Pizza> generateFinalCatalog();
}
