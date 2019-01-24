package com.qa.persistence.domain;

import javax.persistence.*;

@Entity
public class User {

	@Id
	private int userId;
	private String userName;
	private String userPassword;
	
	public User() {
		//meant to be empty
	}
	
	public User(String userName, String userPassword) {
		setUserName(userName);
		setPassword(userPassword);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setPassword(String password) {
		this.userPassword = password;
	}
	
}
