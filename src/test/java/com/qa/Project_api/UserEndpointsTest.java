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
import com.qa.rest.RecipeEndpoints;

@RunWith(MockitoJUnitRunner.class)
public class UserEndpointsTest {
	
	private static final String MOCKVALUE = "test_value_string";
	
	private static final String MOCKVALUE2 = "test_value_string_2";

	@InjectMocks
	private RecipeEndpoints endpoint;
	
	@Mock
	private RecipeService service;
	
	@Before
	public void setup() {
		endpoint.setService(service);
	}
	
	@Test
	public void testGetAllRecipes() {
		Mockito.when(service.getAllRecipes()).thenReturn(MOCKVALUE);
		assertEquals("Did not fetch",MOCKVALUE, endpoint.getAllRecipes());
	}
	
	@Test
	public void testCreateRecipe() {
		Mockito.when(service.addRecipe(MOCKVALUE2, MOCKVALUE2)).thenReturn(MOCKVALUE);
		assertEquals("didnt create", MOCKVALUE, endpoint.addRecipe(MOCKVALUE2, MOCKVALUE2));
		Mockito.verify(service).addRecipe(MOCKVALUE2, MOCKVALUE2);
	}
	
	@Test
	public void testDeleteRecipeById() {
		Mockito.when(service.removeRecipeById(1)).thenReturn(MOCKVALUE);
		assertEquals("didnt remove" , MOCKVALUE, endpoint.removeAccountById(1));
		Mockito.verify(service).removeRecipeById(1);
	}
	
	@Test
	public void testGetMyRecipes() {
		Mockito.when(service.getRecipesByUser(MOCKVALUE2)).thenReturn(MOCKVALUE);
		assertEquals("didnt find",MOCKVALUE , endpoint.getRecipesByUser(MOCKVALUE2));
	}
}