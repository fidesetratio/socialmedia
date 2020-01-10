package com.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Patar.Tambunan
 * 
 *
 */
@SpringBootApplication
@Configuration
@ImportResource("classpath:boot.xml")
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		
	}

}
