package com.qa.persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.persistence.domain.Recipe;
import com.qa.util.JSONUtil;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;
import java.util.Collection;

@Transactional(SUPPORTS)
@Default
public class RecipeDBRepository implements RecipeRepository {

	@PersistenceContext(unitName="primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	
	public String getAllRecipes() {
		Query query = manager.createQuery("SELECT a FROM Recipe a");
		@SuppressWarnings("unchecked")
		Collection<Recipe> recipes = (Collection<Recipe>) query.getResultList();
		return util.getJSONForObject(recipes);
	}

	@Transactional(REQUIRED)
	public String removeRecipeById(int id) {
		Recipe toRemove = findRecipe(id);
		if(toRemove != null) {
			manager.remove(toRemove);
			return "{\"message\": \"Recipe removed\"}";
		}
		return "{\"message\": \"Recipe not found\"}";
	}

	
	@Transactional(REQUIRED)
	private Recipe findRecipe(int id) {
		return manager.find(Recipe.class, id);
	}
}
