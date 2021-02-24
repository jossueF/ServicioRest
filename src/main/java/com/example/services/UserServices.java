package com.example.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.models.User;
import com.github.javafaker.Faker;

@Service
public class UserServices {

	private List<User> users = new ArrayList<>();
	
	private Faker faker;
	
	public UserServices(Faker faker) {
		this.faker = faker;
	}
	
	@PostConstruct
	public void init() {
		for(int i = 0; i < 10; i++) {
			LocalDateTime fecha = LocalDateTime.now();
			users.add(new User(faker.dragonBall().character(), faker.animal().name(), fecha.toString()));
		}	
	}
	
	public List<User> getUsers(String startWith) {
		if(startWith != null) {
			return users.stream()
					.filter(u -> u.getUsername().toLowerCase().startsWith(startWith.toLowerCase()))
					.collect(Collectors.toList());
		}
		else {
			return users;
		} 
	}
	
	public User getByUsername(String username) {
		return users.stream()
				.filter(u -> u.getUsername().toLowerCase().equals(username.toLowerCase()))
				.findAny()
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found.", username)));	
	}
	
	public User createUser(User user) {
		if(users.stream().anyMatch(u -> u.getUsername().toLowerCase().equals(user.getUsername().toLowerCase()))) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s already exists.", user.getUsername()));
		}
		else {
			user.setFechaCreacion(LocalDateTime.now().toString());
			users.add(user);
		}
		return user;
	}
	
	public User updateUser(User user, String username) {
		User userToBeUpdated = getByUsername(username);
		userToBeUpdated.setPassword(user.getPassword());
		userToBeUpdated.setFechaCreacion(LocalDateTime.now().toString());
		return userToBeUpdated;
	}
	
	public void deleteUser(String username) {
		users.remove(getByUsername(username));
	}
}
