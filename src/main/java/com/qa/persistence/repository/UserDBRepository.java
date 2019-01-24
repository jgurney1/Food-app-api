package com.qa.persistence.repository;


import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.persistence.domain.User;
import com.qa.util.JSONUtil;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

@Transactional(SUPPORTS)
@Default
public class UserDBRepository implements UserRepository {

	@PersistenceContext(unitName ="primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	@Transactional(REQUIRED)
	public String addAccount(String account) {
		User newAccount = util.getObjectForJSON(account, User.class);
		manager.persist(newAccount);
		return "{\"message\": \"Account added\"}";
	}

	@Transactional(REQUIRED)
	public String removeAccount(int id) {
		User toRemove = findUser(id);
		if(toRemove != null) {
			return "{\"message\": \"Account removed\"}";
		}
		return "{\"message\": \"Account not found\"}";
	}

	@Transactional(REQUIRED)
	private User findUser(int id) {
		return manager.find(User.class, id);
	}

}
