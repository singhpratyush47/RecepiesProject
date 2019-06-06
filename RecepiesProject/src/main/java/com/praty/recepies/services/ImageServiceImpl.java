package com.praty.recepies.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.praty.recepies.domain.Recipe;
import com.praty.recepies.repositories.RecipeRepository;

@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}

	@Override
	@Transactional
	public void saveImageFile(Long id, MultipartFile file) {
		try {
			Recipe recipe=recipeRepository.findById(id).get();
			Byte[] byteObjects=new Byte[file.getBytes().length];
			int i=0;
			for(byte b:file.getBytes()) {
				byteObjects[i++]=b;
			}
			recipe.setImage(byteObjects);
			recipeRepository.save(recipe);
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}

}
