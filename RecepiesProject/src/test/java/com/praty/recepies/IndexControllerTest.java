package com.praty.recepies;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashSet;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.praty.recepies.controller.IndexController;
import com.praty.recepies.domain.Recipe;
import com.praty.recepies.services.RecipeService;

public class IndexControllerTest {

	@Mock
	RecipeService recepieService;
	@Mock
	Model model;
	IndexController indexController;
	@Mock
	HttpSession session;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		indexController=new IndexController(recepieService);
	}
	
	@Test
	public void testIndexPage() throws Exception {

		String viewName=indexController.getIndexPage(model,session);
		assertEquals(viewName, "index");
		verify(recepieService,times(1)).setOfRecepies();
		verify(model,times(1)).addAttribute("recipes",recepieService.setOfRecepies());
	}
}
