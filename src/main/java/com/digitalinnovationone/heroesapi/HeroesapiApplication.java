package com.digitalinnovationone.heroesapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;


@EnableDynamoDBRepositories
@SpringBootApplication
public class HeroesapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroesapiApplication.class, args);
		System.out.println("Super poderes com WebFlux");

	}
}
