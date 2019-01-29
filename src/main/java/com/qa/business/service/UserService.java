package com.qa.business.service;

public interface UserService {
	
	String addAccount(String account);
	String removeAccount(String email);
	String showAllAccounts();
	String verifyAccount(String account);
	String updateAccount(String account);
	
}
