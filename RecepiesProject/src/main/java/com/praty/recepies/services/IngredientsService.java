package com.praty.recepies.services;

import com.praty.recepies.commands.IngredientCommand;

public interface IngredientsService {

	IngredientCommand findByRecipeIdAndIngredientId(Long recipeId,Long ingredientId);
	
	IngredientCommand saveIngredientCommand(IngredientCommand command);
	
	void deleteById(Long recipeId,Long ingredientId);
}
