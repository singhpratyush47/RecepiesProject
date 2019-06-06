package com.praty.recepies.services;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praty.recepies.commands.IngredientCommand;
import com.praty.recepies.converters.IngredientToIngredientCommand;
import com.praty.recepies.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.praty.recepies.domain.Ingredient;
import com.praty.recepies.domain.Recipe;
import com.praty.recepies.domain.UnitOfMeasure;
import com.praty.recepies.repositories.RecipeRepository;
import com.praty.recepies.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IngredientsServiceImpl implements IngredientsService {

	@Autowired
	RecipeRepository recipeRepo;
	@Autowired
	UnitOfMeasureRepository uomRepo;
	
	@Override
	public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {
		
		Optional<Recipe> recipeOptional=recipeRepo.findById(recipeId);
		Recipe recipe=recipeOptional.get();
		Set<Ingredient> setOfIngredients=recipe.getIngredients();
		if(setOfIngredients!=null && setOfIngredients.size()>0) {
			for(Ingredient ingredient:setOfIngredients) {
				if(ingredient.getId().equals(ingredientId)) {
					UnitOfMeasureToUnitOfMeasureCommand uomConverter=new UnitOfMeasureToUnitOfMeasureCommand();
					IngredientToIngredientCommand ingredientToIngredientCommand=
							new IngredientToIngredientCommand(uomConverter);
					return ingredientToIngredientCommand.convert(ingredient);
				}
			}
		}
		return null;
	}

	@Override
	@Transactional
	public IngredientCommand saveIngredientCommand(IngredientCommand command) {
		
		Optional<Recipe> optionalRecipe=recipeRepo.findById(command.getRecipeId());
		Recipe recipe=optionalRecipe.get();
		Set<Ingredient> setOfIngredients=recipe.getIngredients();
		if(setOfIngredients!=null && !setOfIngredients.isEmpty()) {
			Ingredient actualIngredient=new Ingredient();
			for(Ingredient ingredient:setOfIngredients) {
				if(ingredient.getId().equals(command.getId())) {
					actualIngredient=ingredient;
				}
			}
			actualIngredient.setDescription(command.getDescription());
			actualIngredient.setAmount(command.getAmount());
			Optional<UnitOfMeasure> optionalUnitOfMeasure=
					uomRepo.findById(command.getUnitOfMeasure().getId());
			UnitOfMeasure unitOfMeasure=optionalUnitOfMeasure.get();
			actualIngredient.setUnitOfMeasure(unitOfMeasure);
			recipe.addIngredient(actualIngredient);
		}
		Recipe savedRecipe =recipeRepo.save(recipe);
		Set<Ingredient> setOfSavedIngredients=savedRecipe.getIngredients();
		Ingredient actualIngredient=new Ingredient();
		for(Ingredient ingredient:setOfIngredients) {
			if(ingredient.getId().equals(command.getId())) {
				actualIngredient=ingredient;
			}
		}
		UnitOfMeasureToUnitOfMeasureCommand uomConverter=new UnitOfMeasureToUnitOfMeasureCommand();
		IngredientToIngredientCommand command2=new IngredientToIngredientCommand(uomConverter);
		return command2.convert(actualIngredient);
	}

	@Override
	public void deleteById(Long recipeId, Long ingredientId) {
		Optional<Recipe> optionalRecipe=recipeRepo.findById(recipeId);
		if(optionalRecipe.isPresent()) {
			Recipe recipe=optionalRecipe.get();
			Set<Ingredient> setOfIngredients=recipe.getIngredients();
			Ingredient finalIngredient=new Ingredient();
			for(Ingredient ingredient:setOfIngredients) {
				if(ingredient.getId().equals(ingredientId)) {
					finalIngredient=ingredient;
				}
			}
			finalIngredient.setRecipe(null);
			setOfIngredients.remove(finalIngredient);
			recipeRepo.save(recipe);
		}else {
			
		}
	}

}
