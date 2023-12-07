package com.kdt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class FileConfig implements WebMvcConfigurer{
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:c:/uploads/");
		registry.addResourceHandler("/tracks/image/**").addResourceLocations("file:c:/tracks/image/");
		registry.addResourceHandler("/tracks/**").addResourceLocations("file:c:/tracks/");
	}
}
