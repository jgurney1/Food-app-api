package com.qa.persistence.repository;

public interface UserRepository {

	String addAccount(String account);
	String removeAccount(String email);
	String showAllAccounts();
	String verifyAccount(String account);
	
}
