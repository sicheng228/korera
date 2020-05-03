package com.itlize.Korera;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KoreraApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoreraApplication.class, args);
	}

}
