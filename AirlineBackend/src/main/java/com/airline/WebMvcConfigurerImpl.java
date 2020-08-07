package com.airline;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@FunctionalInterface
public interface WebMvcConfigurerImpl extends WebMvcConfigurer {
	
	void addCorsMappings(CorsRegistry registry);

}
