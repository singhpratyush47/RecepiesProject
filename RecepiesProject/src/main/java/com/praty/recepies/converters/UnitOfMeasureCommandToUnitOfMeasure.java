package com.praty.recepies.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.praty.recepies.commands.UnitOfMeasureCommand;
import com.praty.recepies.domain.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        if (source == null) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setUnitOfMeasure(source.getUnitOfMeasure());
        return uom;
    }
}