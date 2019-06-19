package com.praty.recepies.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.praty.recepies.commands.RecipeCommand;
import com.praty.recepies.services.ImageService;
import com.praty.recepies.services.RecipeService;


@Controller
public class ImageController {

	ImageService imgService;
	RecipeService recipeService;
	
	public ImageController(ImageService imgService, RecipeService recipeService) {
		super();
		this.imgService = imgService;
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{id}/image")
	public String showImageUploadForm(@PathVariable String id,Model model) throws NumberFormatException{
		RecipeCommand command=recipeService.findCommandById(Long.valueOf(id));
		model.addAttribute("recipe",command);
		return "recepies/imageuploadform";
	}
	
	@PostMapping("/recipe/{id}/image")
	public String handleImagePost(@PathVariable String id,@RequestParam("imagefile") MultipartFile file) {
		imgService.saveImageFile(Long.valueOf(id), file);
		return "redirect:/recipe/show/"+id;
	}
	
	@GetMapping("/recipe/{recipeID}/recipeimage")
	public void renderImageFromDatabase(@PathVariable String recipeID,HttpServletResponse response) throws IOException, NumberFormatException{
		RecipeCommand recipeCommand=recipeService.findCommandById(Long.valueOf(recipeID));
		byte[] byteArray=new byte[recipeCommand.getImage().length];
		int i=0;
		for(Byte bite:recipeCommand.getImage()) {
			byteArray[i++]=bite;
		}
		response.setContentType("image/jpeg");
		InputStream inputStream=new ByteArrayInputStream(byteArray);
		IOUtils.copy(inputStream, response.getOutputStream());
	}
}
