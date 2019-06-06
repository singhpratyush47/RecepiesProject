package com.praty.recepies.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.praty.recepies.domain.Category;

public interface CategoryRepository extends CrudRepository<Category,Long> {

	public Optional<Category> findByDescription(String description);
}
