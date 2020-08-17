package com.airline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EntityScan("AirlineJPA")
public class AirlineBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirlineBackendApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurerImpl corsConfigurer() {

		return registry -> registry.addMapping("/api/*").allowedOrigins("http://localhost:4200");
		
	}
	
	@Bean
	public PasswordEncoder createPassEnc() {
		return new BCryptPasswordEncoder();
	}

}
