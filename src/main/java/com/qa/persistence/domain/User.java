package com.qa.persistence.domain;

import javax.persistence.*;

@Entity
public class User {

	@Id
	private String email;
	private String userPassword;
	
	public User() {
		//meant to be empty
	}
	
	public User(String email, String userPassword) {
		setPassword(userPassword);
		setEmail(email);
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setPassword(String password) {
		this.userPassword = password;
	}
	
}
