package guru.springframework.bootstrap;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.domain.Category;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PerfectGuacamole implements ApplicationListener<ContextRefreshedEvent> {

	CategoryRepository categoryRepository;
	UnitOfMeasureRepository uomRepository;
	RecipeRepository recipeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		Recipe recipe = new Recipe();

		Set<Category> categories = getCategories();
		int cookTime = 30;
		String description = "The BEST guacamole! So easy to make with ripe avocados, salt, serrano chiles, cilantro and lime. Garnish with red radishes or jicama. Serve with tortilla chips.";
		Difficulty difficulty = Difficulty.EASY;
		String directions = "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n\n2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n\n3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n\nAdd the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n\nRemember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n\n4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n\nChilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.";
		Set<Ingredient> ingredients = prepareIngredients(recipe);
		Notes notes = new Notes();
		notes.setRecipeNotes(
				"Be careful handling chiles if using. Wash your hands thoroughly after handling and do not touch your eyes or the area near your eyes with your hands for several hours.");
		notes.setRecipe(recipe);
		int prepTime = 10;
		int servings = 4;

		categoryRepository.saveAll(categories);

		recipe.getCategories().addAll(categories);
		recipe.setCookTime(cookTime);
		recipe.setDescription(description);
		recipe.setDifficulty(difficulty);
		recipe.setDirections(directions);
		recipe.getIngredients().addAll(ingredients);
		recipe.setNotes(notes);
		recipe.setPrepTime(prepTime);
		recipe.setServings(servings);
		recipe.setSource("SimplyRecipes");
		recipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

		recipeRepository.save(recipe);

	}

	private Set<Category> getCategories() {
		String[] arr = { "Italian", "Mexican" };
		Set<Category> categories = new HashSet<Category>();
		Arrays.asList(arr).forEach(ele -> {
			Category category = categoryRepository.findByDescription(ele).get();

			categories.add(category);
		});
		return categories;
	}

	private Set<Ingredient> prepareIngredients(Recipe recipe) {

		// Ingredients
		Set<Ingredient> ingredients = new HashSet<Ingredient>();

		// Avocado
		Ingredient avocado = new Ingredient();
		avocado.setAmount(new BigDecimal(2));
		avocado.setDescription("2 ripe avocados");
		avocado.setUom(uomRepository.findByDescription("Nos").get());
		avocado.setRecipe(recipe);

		// Salt
		Ingredient salt = new Ingredient();
		salt.setAmount(new BigDecimal(0.5));
		salt.setDescription("1/2 teaspoon Kosher salt");
		salt.setUom(uomRepository.findByDescription("Teaspoon").get());
		salt.setRecipe(recipe);

		// Lime
		Ingredient lime = new Ingredient();
		lime.setAmount(new BigDecimal(1));
		lime.setDescription("1 Tbsp of lime juice or lemon juice");
		lime.setUom(uomRepository.findByDescription("Tablespoon").get());
		lime.setRecipe(recipe);

		// Onion
		Ingredient onion = new Ingredient();
		onion.setAmount(new BigDecimal(2));
		onion.setDescription("2 Tbsp to 1/4 cup of minced red onion or thinly sliced green onion");
		onion.setUom(uomRepository.findByDescription("Tablespoon").get());
		onion.setRecipe(recipe);

		// Chiles
		Ingredient chile = new Ingredient();
		chile.setAmount(new BigDecimal(1));
		chile.setDescription("1-2 serrano chiles, stems and seeds removed, minced");
		chile.setUom(uomRepository.findByDescription("Nos").get());
		chile.setRecipe(recipe);

		// Cilantro
		Ingredient cilantro = new Ingredient();
		cilantro.setAmount(new BigDecimal(2));
		cilantro.setDescription("2 tablespoons cilantro(leavesand tender stems), finely chopped");
		cilantro.setUom(uomRepository.findByDescription("Tablespoon").get());
		cilantro.setRecipe(recipe);

		// Pepper
		Ingredient pepper = new Ingredient();
		pepper.setAmount(new BigDecimal(1));
		pepper.setDescription("A dash of freshly grated black pepper");
		pepper.setUom(uomRepository.findByDescription("Dash").get());
		pepper.setRecipe(recipe);

		// Tomato
		Ingredient tomato = new Ingredient();
		tomato.setAmount(new BigDecimal(0.5));
		tomato.setDescription("1/2 ripe tomato, seeds and pulp removed, chopped");
		tomato.setUom(uomRepository.findByDescription("Nos").get());
		tomato.setRecipe(recipe);

		ingredients.add(avocado);
		ingredients.add(salt);
		ingredients.add(lime);
		ingredients.add(onion);
		ingredients.add(chile);
		ingredients.add(cilantro);
		ingredients.add(pepper);
		ingredients.add(tomato);

		return ingredients;

	}

}
