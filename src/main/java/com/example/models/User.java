package com.example.models;

public class User {

	private String username;
	private String password;
	private String fechaCreacion;

	public User() {
	}

	public User(String username, String password, String fechaCreacion) {
		this.username = username;
		this.password = password;
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

}
