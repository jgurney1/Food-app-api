package com.qa.business.service;

import javax.inject.Inject;

import com.qa.persistence.repository.RecipeRepository;

public class RecipeServiceImpl implements RecipeService {

	@Inject
	private RecipeRepository repo;
	
	public String getAllRecipes() {
		
		return repo.getAllRecipes();
	}

}
