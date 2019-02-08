package com.qa.Project_api;

import org.junit.*;

import com.qa.business.service.RecipeServiceImpl;
import com.qa.util.Constants;

public class RecipeServiceTest {
	
	private RecipeServiceImpl service;
	
	private static final String PROFBAD = "some text with fucking profanity";
	
	private static final String PROFGOOD = "some text without profanity";
	
	@Before
	public void setup() {
		service = new RecipeServiceImpl();
	}
	
	//write test for add recipe
	
	@Test
	public void testCheckProfanity() {
		Assert.assertEquals("Did not find profanity", false, service.checkProfanity(PROFBAD));
	}
	
	@Test
	public void testCheckProfanity2() {
		Assert.assertEquals("Found profanity", true, service.checkProfanity(PROFGOOD));
	}
	
	@Test
	public void testProfanities() {
		for(String swear: Constants.PROFANITY) {
			Assert.assertEquals("Didnt identify swears", false, service.checkProfanity(swear));
		}
	}
}