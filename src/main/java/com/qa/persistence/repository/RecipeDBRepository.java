package com.qa.persistence.repository;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;

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

	public String getRecipesByUser(String email) {
		Query query = manager.createQuery("SELECT a FROM Recipe a WHERE a.user LIKE '"+ email + "'");
		@SuppressWarnings("unchecked")
		Collection<Recipe> myRecipes = (Collection<Recipe>) query.getResultList();
		return util.getJSONForObject(myRecipes);
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

	@Transactional(REQUIRED)
	public String addRecipe(String email, String recipe) {
		try {
			Recipe newRecipe = util.getObjectForJSON(recipe, Recipe.class);
			newRecipe.setUser(email);
			manager.persist(newRecipe);
			return "{\"message\": \"Recipe added as "+ email + "\"}";
		}
		catch (Exception e) {
			return "{\"message\": \"Failed to add recipe check fields or saved recipes and try again\"}";
		}
	}
}
