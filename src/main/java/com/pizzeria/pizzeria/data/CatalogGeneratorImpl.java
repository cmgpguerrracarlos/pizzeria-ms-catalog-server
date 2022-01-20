package com.pizzeria.pizzeria.data;

import com.pizzeria.pizzeria.model.Pizza;
import com.pizzeria.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

public class CatalogGeneratorImpl implements CatalogGenerator {
    private List<String> toppings = asList("Queso", "Anana", "Peperoni", "Cebolla");
    private List<String> tamanos = asList("Chica", "Mediana", "Grande");
    private List<String> estilo = asList("napolitana", "yankee");
    @Autowired
    private PizzaRepository pizzaRepository;

    public static void main(String[] args) {
        //List<String> catalog = new CatalogGeneratorImpl().buildCsv();
        //catalog.stream().forEach(System.out::println);
    }

    public List<Pizza> generateFinalCatalog(){
        List<String> catalog = new CatalogGeneratorImpl().buildCsv();
        List<Pizza> pizzas = new ArrayList<>();
        for(String pizza:catalog){
            String [] data = pizza.split(",");
            String uid = data[0];
            Float price = Float.parseFloat(data[4]);
            String style = data[1];
            String size = data[3];
            String topping = data[2];
            Pizza pi = new Pizza(uid,price,style,size,topping);
            pizzas.add(pi);
        }
        pizzaRepository.saveAll(pizzas);
        return pizzas;
    }

    public List<String> buildCsv(){
        List<String> pizzas = generateCatalog();
        return pizzas.stream().map(this::buildCsvRow).collect(Collectors.toList());

    }

    private String buildCsvRow(String csv){
        String uid = buildUid(csv.split(","));
        String price = String.valueOf(calculatePrice(uid));

        return asList(uid, csv, price).stream()
                .collect(Collectors.joining(","));
    }

    private List<String> combine(List<String> list1, List<String> list2) {
        return list1.stream()
                .flatMap(str1 -> list2.stream()
                        .map(str2 -> str1 + "," + str2))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> generateCatalog() {
        List<List<String>> catalog = asList(toppings, tamanos);
        return  catalog.stream()
                .reduce(estilo, this::combine);


    }

    @Override
    public String buildUid(String... elements) {
        return stream(elements)
                .reduce("", (acumulado, str) -> acumulado + str.charAt(0));
    }


    @Override
    public float calculatePrice(String uid) {
        return uid.chars().sum();
    }
}
