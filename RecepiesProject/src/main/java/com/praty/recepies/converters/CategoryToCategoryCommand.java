package com.praty.recepies.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.praty.recepies.commands.CategoryCommand;
import com.praty.recepies.domain.Category;

import lombok.Synchronized;

@Component
public class CategoryToCategoryCommand implements Converter<Category,CategoryCommand> {

	@Synchronized
	@Nullable
	@Override
	public CategoryCommand convert(Category source) {
		if(source==null) {
			return null;
		}
		
		CategoryCommand categoryCommand=new CategoryCommand();
		categoryCommand.setId(source.getId());
		categoryCommand.setDescription(source.getDescription());
		return categoryCommand;
	}

}
