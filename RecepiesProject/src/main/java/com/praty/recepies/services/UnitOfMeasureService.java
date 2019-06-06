package com.praty.recepies.services;

import java.util.Set;

import com.praty.recepies.commands.UnitOfMeasureCommand;

public interface UnitOfMeasureService {

	Set<UnitOfMeasureCommand> listAllUoms();
}
