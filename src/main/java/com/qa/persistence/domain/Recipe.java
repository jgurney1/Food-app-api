package com.qa.persistence.domain;

import javax.persistence.*;

@Entity
public class Recipe {

	@Id
	private int recipeId;
	private static int idCounter;
	private String title;
	private String readyTime;
	private String servings;
	private String ingredients;
	private String method;
	
	public Recipe() {
		//meant to be empty
	}
	
	public Recipe(int recipeId, String title, String readyTime, String servings) {
		setRecipeId(recipeId);
		setTitle(title);
		setReadyTime(readyTime);
		setServings(servings);
	}
	
	public Recipe(String title, String readyTime, String servings, String ingredients, String method) {
		setRecipeId(setUnknownRecipeId());
		setTitle(title);
		setReadyTime(readyTime);
		setServings(servings);
		setIngredients(ingredients);
		setMethod(method);
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	
	public int setUnknownRecipeId() { 
		setIdCounter(getIdCounter() + 1);
		return getIdCounter();
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReadyTime() {
		return readyTime;
	}

	public void setReadyTime(String readyTime) {
		this.readyTime = readyTime;
	}

	public String getServings() {
		return servings;
	}

	public void setServings(String servings) {
		this.servings = servings;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Recipe.idCounter = idCounter;
	}
	
	
}
