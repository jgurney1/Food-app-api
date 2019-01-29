package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repo;
	
	public String addAccount(String account) {
		return repo.addAccount(account);
	}

	public String removeAccount(String email) {
		return repo.removeAccount(email);
	}

	public String showAllAccounts() {
		return repo.showAllAccounts();
	}

	public String verifyAccount(String account) {
		return repo.verifyAccount(account);
	}

	public String updateAccount(String account) {
		return repo.updateAccount(account);
	}

}
