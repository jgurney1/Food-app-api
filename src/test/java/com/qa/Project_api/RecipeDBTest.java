package com.qa.Project_api;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.Recipe;
import com.qa.persistence.repository.RecipeDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class RecipeDBTest {
	@InjectMocks
	private RecipeDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"recipeId\":55,\"title\":\"A recipe\",\"readyTime\":\"60\",\"servings\":\"10\"}]";

	private static final String MOCK_OBJECT = "{\"recipeId\":55,\"title\":\"A Recipe\",\"readyTime\":\"60\",\"servings\":\"10\"}";
	
	private static final String MOCK_OBJECT2 = "{\"recipeId\":55,\"title\":\"A Recipe\",\"readyTime\":\"60\",\"servings\":\"10\",\"ingredients\":\"bla bla bla\",\"method\":\"something else\"}";


	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllRecipes() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(new Recipe(55, "A recipe", "60", "10"));
		Mockito.when(query.getResultList()).thenReturn(recipes);
		Assert.assertEquals("Recipes not found", MOCK_DATA_ARRAY, repo.getAllRecipes());
	}

	@Test
	public void testCreateRecipe() {
		String reply = repo.addRecipe("Guest", MOCK_OBJECT);
		Assert.assertEquals("Did not add recipe", reply, "{\"message\": \"Recipe added as Guest\"}");
	}

	@Test
	public void testCreateRecipe2() {
		Recipe rec = new Recipe(55, "A recipe", "60", "10");
		Mockito.when(manager.find(Recipe.class, 55)).thenReturn(rec);
		Assert.assertEquals("Falsely added recipe", repo.addRecipe(MOCK_OBJECT, null), "{\"message\": \"Failed to add recipe check fields or saved recipes and try again\"}");
	}
	
	@Test
	public void testCreateRecipe3() {
		Recipe rec = new Recipe(56, "A recipe", "60", "10", "some ingredients", "some method");
		Mockito.when(manager.find(Recipe.class, 55)).thenReturn(rec);
		Assert.assertEquals("Did not add recipe", repo.addRecipe("Guest", MOCK_OBJECT2), "{\"message\": \"Recipe added as Guest\"}");
	}
	
	@Test
	public void testDeleteRecipeById() {
		String reply = repo.removeRecipeById(1);
		Assert.assertEquals("Recipe found", reply, "{\"message\": \"Recipe not found\"}");
	}
	
	@Test
	public void testDeleteRecipeById2() {
		Recipe usr = new Recipe(55, "A recipe", "60", "10");
		Mockito.when(manager.find(Recipe.class, 55)).thenReturn(usr);
		Assert.assertEquals("Recipe not removed", repo.removeRecipeById(55), "{\"message\": \"Recipe removed\"}" );
	}
	
	@Test
	public void testGetRecipesByUser() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Recipe> recipes = new ArrayList<Recipe>();
		recipes.add(new Recipe(55, "A recipe", "60", "10"));
		Mockito.when(query.getResultList()).thenReturn(recipes);
		Assert.assertEquals("Recipes not found", MOCK_DATA_ARRAY, repo.getRecipesByUser("Guest"));
	}
	
	@Test
	public void testGetRecipesByUser2() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Recipe> recipes = new ArrayList<Recipe>();
		Mockito.when(query.getResultList()).thenReturn(recipes);
		Assert.assertEquals("Recipes found", "{\"message\": \"No recipes by that user found\"}", repo.getRecipesByUser("Guest"));
	}
}