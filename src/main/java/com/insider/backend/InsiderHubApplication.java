package com.insider.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Insider Hub API", version = "1.0", description = "API documentation"))
public class InsiderHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(InsiderHubApplication.class, args);
	}

}
