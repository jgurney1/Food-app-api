package com.qa.rest;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	
	@Path("/removerecipebyid/{id}")
	@DELETE
	@Produces({"application/json"})
	public String removeAccountById(@PathParam("id") int id) {
		return service.removeRecipeById(id);
	}
	
	@Path("/addrecipe/{id}")
	@POST
	@Produces({"application/json"})
	public String addRecipe(@PathParam("id") String user, String recipe) {
		return service.addRecipe(user,recipe);
	}
	
	@Path("/getMyRecipes/{id}")
	@GET
	@Produces({"application/json"})
	public String getRecipesByUser(@PathParam("id") String email) {
		return service.getRecipesByUser(email);
	}
	
	public void setService(RecipeService service) {
		this.service = service;
	}
	
}	
