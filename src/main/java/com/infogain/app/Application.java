/*******************************************************************************
 * Copyright (c) 2018 Infogain.
 *******************************************************************************/
package com.infogain.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



/**
 * Entry point of application.
 * 
 * @author Manish Kumar
 * @since Sep 12, 2018
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.infogain.app.repository")
@EntityScan(basePackages = "com.infogain.app.entity")
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
}
