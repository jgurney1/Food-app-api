package com.qa.persistence.domain;

import javax.persistence.*;

@Entity
public class User {

	@Id
	private int userId;
	private String userName;
	private String password;
	
	public User() {
		//meant to be empty
	}
	
	public User(String userName, String password) {
		setUserName(userName);
		setPassword(password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
