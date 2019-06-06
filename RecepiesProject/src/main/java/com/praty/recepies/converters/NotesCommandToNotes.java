package com.praty.recepies.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.praty.recepies.commands.NotesCommand;
import com.praty.recepies.domain.Notes;

import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand,Notes> {

	@Synchronized
	@Nullable
	@Override
	public Notes convert(NotesCommand source) {
		if(source==null) {
			return null;
		}
		final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecepieNotes(source.getRecepieNotes());
        return notes;
	}

}
