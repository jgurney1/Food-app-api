package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.domain.Recipe;
import com.qa.persistence.repository.RecipeRepository;
import com.qa.util.Constants;
import com.qa.util.JSONUtil;

public class RecipeServiceImpl implements RecipeService {

	@Inject
	private RecipeRepository repo;
	
	@Inject
	private JSONUtil util;
	
	public String getAllRecipes() {
		return repo.getAllRecipes();
	}

	public String removeRecipeById(int id) {
		return repo.removeRecipeById(id);
	}

	public String addRecipe(String email, String recipe) {
		try {
			Recipe rec = util.getObjectForJSON(recipe, Recipe.class);
			String title = rec.getTitle();
			if(checkProfanity(title)) {
				return repo.addRecipe(email, recipe);
			}
			return "{\"message\": \"Recipe title cannot contain Profanity\"}";
		}
		catch (Exception e) {
			return "{\"message\": \"Error saving recipe check inputs and try again\"}";
		}
	}

	public Boolean checkProfanity(String text) {
		String toSplit = text.toUpperCase();
		String[] words = toSplit.split(" ");
		for(String word: words) {
			for(String swear: Constants.PROFANITY) {
				if(word.equals(swear)) {
					return false;
				}
			}
		}
		return true;
	}

	public String getRecipesByUser(String email) {
		return repo.getRecipesByUser(email);
	}
	
}
