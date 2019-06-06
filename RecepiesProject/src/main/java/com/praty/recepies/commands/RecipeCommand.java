package com.praty.recepies.commands;

import java.util.HashSet;
import java.util.Set;

import com.praty.recepies.domain.Category;
import com.praty.recepies.domain.Difficulty;
import com.praty.recepies.domain.Ingredient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {

	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer serving;
	private String source;
	private String url;
	private String directions;
	private Set<IngredientCommand> ingredients=new HashSet<>();
	private Byte[] image;
	private Difficulty difficulty;
	private NotesCommand notes;
	private Set<CategoryCommand> setOfCategories=new HashSet<>();
}
