package com.qa.business.service;

public interface UserService {
	
	String addAccount(String account);
	String removeAccount(int id);
	String showAllAccounts();
	String verifyAccount(String account);
	
}
