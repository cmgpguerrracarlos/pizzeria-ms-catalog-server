package com.pizzeria.pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PizzeriaServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(PizzeriaServiceApplication.class, args);
	}

}
