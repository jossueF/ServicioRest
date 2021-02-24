package com.example.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Role;
import com.example.services.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Role>> getRoles() {
		log.info("getRoles()");
		return new ResponseEntity<List<Role>>(service.getRoles(), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Role> createRole(@RequestBody Role role) {
		log.info("{}", role);
		return new ResponseEntity<Role>(service.createRole(role), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
	public ResponseEntity<Role> updateRole(@PathVariable("roleId") Integer id, @RequestBody Role role) {
		return new ResponseEntity<Role>(service.updateRole(id, role), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRole(@PathVariable("roleId") Integer id) {
		service.deleteRole(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value = "/{roleId}")
	public ResponseEntity<Role> getRoleById(@PathVariable("roleId") Integer id) {
		return new ResponseEntity<Role>(service.getRoleById(id), HttpStatus.OK);
	}
	

}
