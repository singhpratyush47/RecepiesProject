package com.praty.recepies.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.stream.events.NotationDeclaration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.praty.recepies.commands.RecipeCommand;
import com.praty.recepies.converters.CategoryToCategoryCommand;
import com.praty.recepies.converters.IngredientToIngredientCommand;
import com.praty.recepies.converters.NotesToNotesCommand;
import com.praty.recepies.converters.RecipeToRecipeCommand;
import com.praty.recepies.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.praty.recepies.domain.Category;
import com.praty.recepies.domain.Recipe;
import com.praty.recepies.domain.UnitOfMeasure;
import com.praty.recepies.exceptions.NotFoundException;
import com.praty.recepies.repositories.CategoryRepository;
import com.praty.recepies.services.RecipeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IndexController {

	RecipeService recipeService;
	private static final String RECIPE_RECIPEFORM_URL="recepies/recipeForm";
	public IndexController(RecipeService recipeService) {
		super();
		this.recipeService = recipeService;
	}

	@RequestMapping({"/index","","/"})
	public String getIndexPage(Model model,HttpSession session) {
		log.debug("Inside getIndexPage method of IndexController");
		model.addAttribute("recipes",recipeService.setOfRecepies());
		session.setAttribute("Name", "Krishna");
		return "index";
	}
	
	@RequestMapping({"/recipe/show/{id}"})
	public String showRecepieDescription(@PathVariable String id,Model model) throws NumberFormatException {
		log.debug("Inside showRecepieDescription method of IndexController");
		model.addAttribute("recipe",recipeService.findById(Long.valueOf(id)));
		return "recepies/show";
	}
	
	@GetMapping("recipe/update/{id}")
    public String updateRecipe(@PathVariable String id, Model model) throws NumberFormatException{
		RecipeCommand recipeCommand=recipeService.findCommandById(Long.valueOf(id));
        model.addAttribute("recipe",recipeCommand);
        return  RECIPE_RECIPEFORM_URL;
    }
	
	@RequestMapping("/recipe/openModal")
	public String openModal(@RequestParam("recipeId") String recipeId,HttpServletRequest request,
            HttpServletResponse response) {
		log.debug("Inside openModal method of IndexController");
		request.setAttribute("name","pratyush Singh");
		return "recepies/popup";
	}
	
	@RequestMapping("/recipe/new")
	public String getRecipeForm(Model model) {
		model.addAttribute("recipe",new RecipeCommand());
		return RECIPE_RECIPEFORM_URL;
	}
	
	@PostMapping
	@RequestMapping("recipe")
	public String saveOrUpdate(@Valid  @ModelAttribute("recipe") RecipeCommand command,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(error->{
				log.debug(error.toString());
			});
			return RECIPE_RECIPEFORM_URL;
		}
		RecipeCommand recipeCommand=recipeService.saveRecipeCommand(command);
		return "redirect:/recipe/show/"+recipeCommand.getId();
	}

	@GetMapping("recipe/delete/{id}")
    public String deleteRecipe(@PathVariable String id, Model model){
		recipeService.deleteById(Long.valueOf(id));
        return  "redirect:/";
    }
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("404error");
        modelAndView.addObject("exception", exception);
        return modelAndView;
    }
	
}
