package com.praty.recepies.repositories;

import org.springframework.data.repository.CrudRepository;

import com.praty.recepies.domain.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

}
