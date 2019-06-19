package com.praty.recepies.services;


import java.util.Set;

import com.praty.recepies.commands.RecipeCommand;
import com.praty.recepies.domain.Recipe;

import javassist.NotFoundException;

public interface RecipeService {

	public Set<Recipe> setOfRecepies();

	public Recipe findById(Long id);
	
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
	
	public void deleteById(Long id);

	public RecipeCommand findCommandById(Long l);
}
