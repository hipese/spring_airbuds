package com.kdt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class FileConfig implements WebMvcConfigurer{
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/uploads/**").addResourceLocations("file:/uploads/");
		registry.addResourceHandler("/tracks/image/**").addResourceLocations("file:/tracks/image/");
		registry.addResourceHandler("/tracks/**").addResourceLocations("file:/tracks/");
		registry.addResourceHandler("/backgroundImages/**").addResourceLocations("file:/backgroundImages/");
		registry.addResourceHandler("/profileImages/**").addResourceLocations("file:/profileImages/");
	}
}
