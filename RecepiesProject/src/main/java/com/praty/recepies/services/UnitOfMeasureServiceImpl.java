package com.praty.recepies.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.praty.recepies.commands.UnitOfMeasureCommand;
import com.praty.recepies.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.praty.recepies.domain.UnitOfMeasure;
import com.praty.recepies.repositories.UnitOfMeasureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

	private final UnitOfMeasureRepository unitOfMeasureRepo;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepo,
			UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		super();
		this.unitOfMeasureRepo = unitOfMeasureRepo;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}
	@Override
	public Set<UnitOfMeasureCommand> listAllUoms() {
		Set<UnitOfMeasureCommand> finalSetToReturn=new HashSet<>();
		List<UnitOfMeasure> listOfUnitOfMeasure=(List<UnitOfMeasure>) unitOfMeasureRepo.findAll();
		for(UnitOfMeasure measure:listOfUnitOfMeasure) {
			UnitOfMeasureCommand command=unitOfMeasureToUnitOfMeasureCommand.convert(measure);
			finalSetToReturn.add(command);	
		}
		return finalSetToReturn;
	}

}
