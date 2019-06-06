package com.praty.recepies.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.praty.recepies.commands.RecipeCommand;
import com.praty.recepies.converters.RecipeCommandToRecipe;
import com.praty.recepies.converters.RecipeToRecipeCommand;
import com.praty.recepies.domain.Recipe;
import com.praty.recepies.repositories.RecipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipeRepo;
	@Autowired
	private RecipeCommandToRecipe recipeCommandToRecipe;
	@Autowired
	private RecipeToRecipeCommand recipeToRecipeCommand; 
	
	@Override
	public Set<Recipe> setOfRecepies() {
		log.debug("Inside setOfRecepies method of RecipeServiceImpl");
		Set<Recipe> setOfRecipe=new HashSet<>();
		Iterable<Recipe> iteator=recipeRepo.findAll();
		for(Recipe recipe:iteator) {
			setOfRecipe.add(recipe);
		}
		return setOfRecipe;
	}
	
	public Recipe findById(Long id) {
		Recipe recipe=new Recipe();
		Optional<Recipe> optionalRecipe=recipeRepo.findById(id);
		recipe=optionalRecipe.get();
		return recipe;
	}

	public void deleteById(Long id) {
		recipeRepo.deleteById(id);
	}
	
	@Override
	@Transactional
	public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {
		Recipe detachedRecipe=recipeCommandToRecipe.convert(recipeCommand);
		Recipe savedRecipe=recipeRepo.save(detachedRecipe);
		return recipeToRecipeCommand.convert(savedRecipe);
	}
	
	@Override
    @Transactional
    public RecipeCommand findCommandById(Long l) {
        return recipeToRecipeCommand.convert(findById(l));
    }
}
