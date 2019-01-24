package com.qa.persistence.repository;

public interface UserRepository {

	String addAccount(String account);
	String removeAccount(int id);
	String showAllAccounts();
	
}
