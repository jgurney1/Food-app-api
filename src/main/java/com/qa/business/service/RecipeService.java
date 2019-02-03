package com.qa.business.service;

public interface RecipeService {
	
	String getAllRecipes();
	String removeRecipeById(int id);
	String addRecipe(String email, String recipe);
}
