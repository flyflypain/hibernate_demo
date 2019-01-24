package com.practice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication(scanBasePackages = "com.practice")
@EntityScan("com.practice.model")
@EnableJpaRepositories("com.practice.repository")
public class DemoApplication {

	@Bean
	public BCryptPasswordEncoder getBCryptoPasswordEncoder() {
		return new BCryptPasswordEncoder();
	};

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
