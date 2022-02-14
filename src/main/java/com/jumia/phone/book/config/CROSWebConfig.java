package com.jumia.phone.book.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CROSWebConfig implements WebMvcConfigurer {

	
	 @Override
	    public void addCorsMappings(CorsRegistry registry) {
		 registry.addMapping("/api/**")
         .allowedOrigins("http://localhost:4200","*")
         .allowedMethods("GET", "POST", "OPTIONS", "PUT","DELETE")
         .allowedHeaders("Content-Type", "X-Requested-With", "accept","Origin","authorization")
         //this is other allowed headers but will be added if needed
         //it depends on front end allowed headers
         //,"Access-Control" ,"Access-Control-Allow-Origin","Access-Control-Allow-Headers","Access-Control-Request-Headers"
         .allowCredentials(false).maxAge(3600);
	    }
}
