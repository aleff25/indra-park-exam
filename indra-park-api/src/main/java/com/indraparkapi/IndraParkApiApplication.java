package com.indraparkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class IndraParkApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndraParkApiApplication.class, args);
	}

}
