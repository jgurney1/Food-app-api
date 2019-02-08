package com.qa.persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.persistence.domain.User;
import com.qa.util.JSONUtil;

import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import static javax.transaction.Transactional.TxType.REQUIRED;

@Transactional(SUPPORTS)
@Default
public class UserDBRepository implements UserRepository {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;

	@Transactional(REQUIRED)
	public String addAccount(String account) {
		User newAccount = util.getObjectForJSON(account, User.class);
		if (findUser(newAccount.getEmail()) != null) {
			return "{\"message\": \"Email already taken\"}";
		}
		manager.persist(newAccount);
		return "{\"message\": \"Account added\"}";
	}

	@Transactional(REQUIRED)
	public String removeAccount(String email) {
		User toRemove = findUser(email);
		if (toRemove != null) {
			manager.remove(toRemove);
			return "{\"message\": \"Account removed\"}";
		}
		return "{\"message\": \"Account not found\"}";
	}

	@Transactional(REQUIRED)
	private User findUser(String email) {
		return manager.find(User.class, email);
	}

	public String showAllAccounts() {
		Query query = manager.createQuery("SELECT a FROM User a");
		@SuppressWarnings("unchecked")
		Collection<User> users = (Collection<User>) query.getResultList();
		return util.getJSONForObject(users);
	}

	@Transactional(REQUIRED)
	public String verifyAccount(String account) {
		try {
			User toVerify = util.getObjectForJSON(account, User.class);
			User match = findUser(toVerify.getEmail());
			if (toVerify.getUserPassword().equals(match.getUserPassword())) {
				return "{\"message\": \"Login Successful\"}";
			}
			return "{\"message\": \"Incorrect password\"}";
		} catch (Exception e) {
			return "{\"message\": \"Incorrect password or email\"}";
		}
	}

	@SuppressWarnings("unused")
	@Transactional(REQUIRED)
	public String updateAccount(String account) {
			User toUpdate = util.getObjectForJSON(account, User.class);
			User toremove = findUser(toUpdate.getEmail());
			if (toUpdate != null && toremove != null) {
				manager.remove(toremove);
				manager.persist(toUpdate);
				return "{\"message\": \"Password Updated\"}";
			}
			return "{\"message\": \"Account not found\"}";
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}



}
