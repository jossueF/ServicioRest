package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.entities.Role;
import com.example.repositories.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository repository;

	public List<Role> getRoles() {
		return repository.findAll();
	}
	
	public Role createRole(Role role) {
		return repository.save(role);
	}
	
	public Role updateRole(Integer id, Role role) {
		Optional<Role> result = repository.findById(id);
		if(result.isPresent()) {
			return repository.save(role);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exist.", id));
		}
	}
	
	public void deleteRole(Integer id) {
		Optional<Role> result = repository.findById(id);
		if(result.isPresent()) {
			repository.delete(result.get());
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exist.", id));
		}
	}
	
	public Role getRoleById(Integer id) {
		Optional<Role> result = repository.findById(id);
		if(result.isPresent()) {
			return result.get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id %d doesn't exist.", id));
		}
	}
}
