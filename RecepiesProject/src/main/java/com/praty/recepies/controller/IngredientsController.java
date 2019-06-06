package com.praty.recepies.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.praty.recepies.commands.IngredientCommand;
import com.praty.recepies.commands.RecipeCommand;
import com.praty.recepies.commands.UnitOfMeasureCommand;
import com.praty.recepies.services.IngredientsService;
import com.praty.recepies.services.RecipeService;
import com.praty.recepies.services.UnitOfMeasureService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientsController {

	RecipeService recipeService;
	IngredientsService ingredientsService;
	UnitOfMeasureService uomService;
	
	public IngredientsController(RecipeService recipeService,IngredientsService ingredientsService,UnitOfMeasureService uomService) {
		super();
		this.recipeService = recipeService;
		this.ingredientsService=ingredientsService;
		this.uomService=uomService;
	}

	@RequestMapping({"/recipe/ingredients/{id}"})
	public String getIngredientList(@PathVariable String id,Model model) {
		log.debug("Inside getIngredientList method of IngredientsController");
		RecipeCommand recipeCommand=recipeService.findCommandById(Long.valueOf(id));
		model.addAttribute("recipe",recipeCommand);
		return "recepies/ingredients/list";
	}

	@RequestMapping({"/recipe/{recipeId}/ingredients/{ingredientsId}/show"})
	public String viewIngredient(@PathVariable String recipeId,@PathVariable String ingredientsId,
			Model model) {
		log.debug("Inside viewIngredient method of IngredientsController");
		IngredientCommand ingredientCommand=ingredientsService
				.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientsId));
		model.addAttribute("ingredients",ingredientCommand);
		return "recepies/ingredients/show";
	}

	@RequestMapping({"/recipe/{recipeId}/ingredient/new"})
	public String newIngredient(@PathVariable String recipeId,Model model) {
		log.debug("Inside viewIngredient method of IngredientsController");
		/*IngredientCommand ingredientCommand=ingredientsService
				.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientsId));*/
		/*model.addAttribute("ingredients",ingredientCommand);*/
		return "recepies/ingredients/show";
	}
	
	@RequestMapping({"/recipe/{recipeId}/ingredients/{ingredientsId}/update"})
	public String updateIngredient(@PathVariable String recipeId,@PathVariable String ingredientsId,
			Model model) {
		log.debug("Inside updateIngredient method of IngredientsController");
		IngredientCommand ingredientCommand=ingredientsService
				.findByRecipeIdAndIngredientId(Long.valueOf(recipeId), Long.valueOf(ingredientsId));
		model.addAttribute("ingredients",ingredientCommand);
		Set<UnitOfMeasureCommand> setOfUnitOfMeasureCommand=uomService.listAllUoms();
		model.addAttribute("uomList",setOfUnitOfMeasureCommand);
		return "recepies/ingredients/ingredientform";
	}
	@RequestMapping({"recipe/{recipeId}/ingredient"})
	public String saveOrUpdate(@ModelAttribute IngredientCommand ingredientCommand) {
		log.debug("Inside saveOrUpdate method of IngredientsController");
		IngredientCommand command=ingredientsService.saveIngredientCommand(ingredientCommand);
		return "redirect:/recipe/"+command.getRecipeId()+"/ingredients/"+command.getId()+"/show";
	}


	@RequestMapping({"/recipe/{recipeId}/ingredients/{ingredientsId}/delete"})
	public String deleteIngredient(@PathVariable String recipeId,@PathVariable String ingredientsId,
			Model model) {
		log.debug("Inside deleteIngredient method of IngredientsController");
		ingredientsService.deleteById(Long.valueOf(recipeId), Long.valueOf(ingredientsId));
		return "redirect:/recipe/ingredients/"+recipeId;
	}

}
