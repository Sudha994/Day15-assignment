package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Assignment1Day15ECommerceOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Day15ECommerceOrderServiceApplication.class, args);
	}

}
