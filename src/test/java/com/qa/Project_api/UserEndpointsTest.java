package com.qa.Project_api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.RecipeService;
import com.qa.business.service.UserService;
import com.qa.rest.RecipeEndpoints;
import com.qa.rest.UserEndpoints;

@RunWith(MockitoJUnitRunner.class)
public class UserEndpointsTest {
	
	private static final String MOCKVALUE = "test_value_string";
	
	private static final String MOCKVALUE2 = "test_value_string_2";

	@InjectMocks
	private UserEndpoints endpoint;
	
	@Mock
	private UserService service;
	
	@Before
	public void setup() {
		endpoint.setService(service);
	}
	
	@Test
	public void testGetAllRecipes() {
		Mockito.when(service.showAllAccounts()).thenReturn(MOCKVALUE);
		assertEquals("Did not fetch",MOCKVALUE, endpoint.showAllAccounts());
	}
	
	@Test
	public void testCreateRecipe() {
		Mockito.when(service.addAccount(MOCKVALUE2)).thenReturn(MOCKVALUE);
		assertEquals("didnt create", MOCKVALUE, endpoint.addAcount(MOCKVALUE2));
		Mockito.verify(service).addAccount(MOCKVALUE2);
	}
	
	@Test
	public void testDeleteRecipeById() {
		Mockito.when(service.removeAccount(MOCKVALUE)).thenReturn(MOCKVALUE);
		assertEquals("didnt remove" , MOCKVALUE, endpoint.removeAccount(MOCKVALUE));
		Mockito.verify(service).removeAccount(MOCKVALUE);
	}
	
}