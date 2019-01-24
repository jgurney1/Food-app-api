package com.qa.persistence.repository;

public interface RecipeRepository {

	String getAllRecipes();
	String removeRecipeById(int id);
}
