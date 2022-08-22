package com.nawa.primenogenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This application provides APIs to generate Prime Numbers
 * 
 * @author nawaz
 *
 */
@SpringBootApplication
@EnableScheduling
public class PrimenogeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrimenogeneratorApplication.class, args);
	}

}
