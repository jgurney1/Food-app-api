package com.qa.persistence.repository;

public interface RecipeRepository {

	String getAllRecipes();
	String removeRecipeById(int id);
	String addRecipe(String user, String recipe);
	String getRecipesByUser(String email);
}
