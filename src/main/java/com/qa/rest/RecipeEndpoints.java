package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.qa.business.service.RecipeService;

@Path("/recipe")
public class RecipeEndpoints {
	
	@Inject RecipeService service;
	
	@Path("/getallrecipes")
	@GET
	@Produces({"application/json"})
	public String getAllRecipes() {
		return service.getAllRecipes();
	}
	
}
