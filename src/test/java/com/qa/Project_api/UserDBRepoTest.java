package com.qa.Project_api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.User;
import com.qa.persistence.repository.UserDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class UserDBRepoTest {
	@InjectMocks
	private UserDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"email\":\"John\",\"userPassword\":\"Doe\"}]";

	private static final String MOCK_OBJECT = "{\"email\":\"John\",\"userPassword\":\"Doe\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<User> accounts = new ArrayList<User>();
		accounts.add(new User("John", "Doe"));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		Assert.assertEquals("Accounts not found", MOCK_DATA_ARRAY, repo.showAllAccounts());
	}

	@Test
	public void testCreateAccount() {
		String reply = repo.addAccount(MOCK_OBJECT);
		Assert.assertEquals("Did not add account", reply, "{\"message\": \"Account added\"}");
	}

	@Test
	public void testCreateAccount2() {
		User usr = new User("John", "Doe");
		Mockito.when(manager.find(User.class, "John")).thenReturn(usr);
		Assert.assertEquals("Falsely added account", repo.addAccount(MOCK_OBJECT), "{\"message\": \"Email already taken\"}");
	}
	
	@Test
	public void testDeleteUser() {
		String reply = repo.removeAccount("John");
		Assert.assertEquals("Account found", reply, "{\"message\": \"Account not found\"}");
	}
	
	@Test
	public void testDeleteUser2() {
		User usr = new User("John","Doe");
		Mockito.when(manager.find(User.class, "John")).thenReturn(usr);
		Assert.assertEquals("Account not removed", repo.removeAccount("John"), "{\"message\": \"Account removed\"}" );
	}
	
	@Test
	public void testVerifyAccount() {
		User usr = new User("John", "Doe");
		Mockito.when(manager.find(User.class, "John")).thenReturn(usr);
		String reply = repo.verifyAccount(MOCK_OBJECT);
		Assert.assertEquals("Didnt login success", reply, "{\"message\": \"Login Successful\"}");
	}
	
	@Test
	public void testVerifyAccount2() {
		User usr = new User("John","DoeDoe");
		Mockito.when(manager.find(User.class, "John")).thenReturn(usr);
		String reply = repo.verifyAccount(MOCK_OBJECT);
		Assert.assertEquals("Didnt return incorrect password", reply, "{\"message\": \"Incorrect password\"}");
	}
	
	@Test
	public void testVerifyAccount3() {
		String reply = repo.verifyAccount(MOCK_OBJECT);
		Assert.assertEquals("Didnt login success caught", reply, "{\"message\": \"Incorrect password or email\"}");
	}
	
	@Test
	public void testUpdateAccount() {
		User usr = new User("John","Doe");
		Mockito.when(manager.find(User.class, "John")).thenReturn(usr);
		Assert.assertEquals("Password not updated",repo.updateAccount(MOCK_OBJECT), "{\"message\": \"Password Updated\"}");
	}
	
	@Test
	public void testUpdateAccount2() {
		Mockito.when(manager.find(User.class, "John")).thenReturn(null);
		Assert.assertEquals("Password not updated",repo.updateAccount(MOCK_OBJECT), "{\"message\": \"Account not found\"}");
	}

}