package com.app.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//https://github.com/endymuhardin/training-brainmatics-2015-1/tree/master/aplikasi-pelatihan/src/main/java/com/muhardin/endy/pelatihan/config
@Configuration
public class AppWebConfig implements  WebMvcConfigurer  {
	
		@Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/hello").setViewName("hello");
	        registry.addViewController("/login").setViewName("login");
		};
	    
	    
/*
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(new ThymeleafLayoutInterceptor());
	    }*/
	    
	
	
}
