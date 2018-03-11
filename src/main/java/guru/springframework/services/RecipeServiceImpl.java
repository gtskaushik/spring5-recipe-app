package guru.springframework.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {

	RecipeRepository recipeRepository;

	@Override
	public Set<Recipe> getRecipes() {
		Set<Recipe> recipes = new HashSet<Recipe>();

		recipeRepository.findAll().forEach(recipe -> recipes.add(recipe));
		
		return recipes;
	}

}
