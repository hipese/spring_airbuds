package com.kdt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration 
public class FileConfig {
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:c:/uploads/");
		registry.addResourceHandler("/tracks/**").addResourceLocations("file:c:/tracks/");
	}
}
