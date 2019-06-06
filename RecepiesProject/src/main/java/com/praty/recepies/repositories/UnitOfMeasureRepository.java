package com.praty.recepies.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.praty.recepies.domain.UnitOfMeasure;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure,Long> {

	public Optional<UnitOfMeasure> findByUnitOfMeasure(String unitOfMeasure);
}
