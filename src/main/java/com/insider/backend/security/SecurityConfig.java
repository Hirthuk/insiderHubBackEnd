package com.insider.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.insider.backend.util.JwtUtil;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtUtil jwtUtil) throws Exception {
    	http
    	  .csrf(csrf -> csrf.disable())
    	  .authorizeHttpRequests(auth -> auth
    	      .requestMatchers(
    	          "/api/auth/login",          // your public login
    	          "/v3/api-docs/**",          // OpenAPI docs
    	          "/swagger-ui/**",           // Swagger UI resources
    	          "/swagger-ui.html"          // Swagger UI entry
    	      ).permitAll()
    	      .anyRequest().authenticated()
    	  )
    	  .addFilterBefore(new JwtAuthFilter(jwtUtil),
    	      UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
