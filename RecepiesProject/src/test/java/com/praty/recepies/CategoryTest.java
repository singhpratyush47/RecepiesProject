package com.praty.recepies;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.praty.recepies.domain.Category;


public class CategoryTest {

	Category category;
	
	@Before
	public void setUp() {
		category=new Category();
	}
	
	@Test
	public void getId() {
		Long id=4l;
		category.setId(id);
		assertEquals(id,category.getId());
	}
	
	@Test
	public void getDescription() {
		String description="hello";
		category.setDescription(description);
		assertEquals(description,category.getDescription());
	}
}
