package com.insider.backend.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

  private SecurityScheme bearerScheme() {
    return new SecurityScheme()
        .name("BearerAuth")
        .type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT"); // shows as JWT in UI
  }

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .components(new Components().addSecuritySchemes("BearerAuth", bearerScheme()))
        .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
        .info(new Info()
            .title("InsiderHub API")
            .version("1.0")
            .description("API documentation for InsiderHub backend")
            .contact(new Contact().name("InsiderHub Team").email("sharanclouddev@gmail.com")));
  }
}
