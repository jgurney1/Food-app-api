package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.UserRepository;

public class UserServiceImpl implements UserService {

	@Inject
	private UserRepository repo;
	
	public String addAccount(String account) {
		return repo.addAccount(account);
	}

	public String removeAccount(int id) {
		return repo.removeAccount(id);
	}

}
