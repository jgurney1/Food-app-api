package com.qa.Project_api;

import org.junit.*;

import com.qa.business.service.UserServiceImpl;

public class UserServiceTest {
	
	private UserServiceImpl service;
	
	@Before
	public void setup() {
		service = new UserServiceImpl();
	}
	
	@Test
	public void testCheckProfanity() {
		Assert.assertEquals("Did not find profanity", false, service.checkEmpty("asdf"));
	}
	
	@Test
	public void testCheckProfanity2() {
		Assert.assertEquals("Found profanity", true, service.checkEmpty(""));
	}
	
	@Test
	public void testAddAccount() {
		String reply = service.addAccount("");
		Assert.assertEquals("Wrong return", reply, "{\"message\": \"Please enter account details to add account\"}");
	}
	
	@Test
	public void testRemoveAccount() {
		String reply = service.removeAccount("");
		Assert.assertEquals("Wrong return", reply, "{\"message\": \"Please enter account to remove\"}");
	}
	
	@Test
	public void testVerifyAccount() {
		String reply = service.verifyAccount("");
		Assert.assertEquals("Wrong return", reply, "{\"message\": \"Please enter account details login\"}");
	}
	
	@Test
	public void testUpdateAccount() {
		String reply = service.updateAccount("");
		Assert.assertEquals("Wrong return", reply, "{\"message\": \"Please enter account details to update account\"}");
	}
}