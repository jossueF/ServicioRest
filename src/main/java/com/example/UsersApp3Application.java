package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.entities.User;
import com.example.repositories.UserRepository;
import com.github.javafaker.Faker;

@SpringBootApplication
public class UsersApp3Application implements ApplicationRunner{

	@Autowired
	private Faker faker;
	
	@Autowired
	private UserRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(UsersApp3Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		for(int i = 0; i < 50; i++) {
			User user = new User();
			user.setUsername(faker.name().firstName());
			user.setPassword(faker.dragonBall().character());
			user.setProfile(null);
			repository.save(user);
		}
	}

}
