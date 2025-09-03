package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Assignment1Day15ECommerceMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assignment1Day15ECommerceMicroservicesApplication.class, args);
	}

}
