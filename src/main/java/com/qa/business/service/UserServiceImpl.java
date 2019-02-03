package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repo;
	
	public String addAccount(String account) {
		if(checkEmpty(account) ) {
			return "{\"message\": \"Please enter account details to add account\"}";
		}
		return repo.addAccount(account);
	}

	public String removeAccount(String email) {
		if(checkEmpty(email)) {
			return "{\"message\": \"Please enter account to remove\"}";
		}
		return repo.removeAccount(email);
	}

	public String showAllAccounts() {
		return repo.showAllAccounts();
	}

	public String verifyAccount(String account) {
		if(checkEmpty(account)) {
			return "{\"message\": \"Please enter account details login\"}";
		}
		return repo.verifyAccount(account);
	}

	public String updateAccount(String account) {
		if(checkEmpty(account)) {
			return "{\"message\": \"Please enter account details to update account\"}";
		}
		return repo.updateAccount(account);
	}

	public Boolean checkEmpty(String toCheck) {
		if (toCheck.equals("")) {
			return true;
		}
		
		return false;
	}
}
