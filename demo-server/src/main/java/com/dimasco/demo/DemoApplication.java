package com.dimasco.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	public ApplicationRunner runner() {
//		return (args) -> {
//			System.out.println("Application started");
//
//			//Flux.just("5-th feeling", "Green mile", "Back to the future");
//		};
//	}
}
	