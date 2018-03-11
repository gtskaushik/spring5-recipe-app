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
public class GrilledChickenTacos implements ApplicationListener<ContextRefreshedEvent> {

	CategoryRepository categoryRepository;
	UnitOfMeasureRepository uomRepository;
	RecipeRepository recipeRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		Recipe recipe = new Recipe();

		Set<Category> categories = getCategories();
		int cookTime = 15;
		String description = "Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. "
				+ "Great for a quick weeknight dinner, backyard cookouts, and tailgate parties.";
		Difficulty difficulty = Difficulty.EASY;
		String directions = "1 Prepare a gas or charcoal grill for medium-high, direct heat." + "\n\n"
				+ "2 Make the marinade and coat the chicken: In a large bowl, stir together "
				+ "the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. "
				+ "Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and "
				+ "toss to coat all over." + "\n\n"
				+ "Set aside to marinate while the grill heats and you prepare the rest of the toppings." + "\n\n"
				+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a "
				+ "thermometer inserted into the thickest part of the meat registers 165F. Transfer to "
				+ "a plate and rest for 5 minutes." + "\n\n"
				+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high "
				+ "heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs "
				+ "and heat for a few seconds on the other side.;;Wrap warmed tortillas in a tea towel to keep them "
				+ "warm until serving." + "\n\n"
				+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of "
				+ "arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. "
				+ "Drizzle with the thinned sour cream. Serve with lime wedges.";
		Set<Ingredient> ingredients = prepareIngredients(recipe);
		Notes notes = new Notes();
		notes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, "
				+ "on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the "
				+ "oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor "
				+ "won't be quite the same.)");
		notes.setRecipe(recipe);
		int prepTime = 20;
		int servings = 6;

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
		recipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

		recipeRepository.save(recipe);

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

	private Set<Category> getCategories() {
		String[] arr = { "American", "Fast Food" };
		Set<Category> categories = new HashSet<Category>();
		Arrays.asList(arr).forEach(ele -> {
			Category category = categoryRepository.findByDescription(ele).get();

			categories.add(category);
		});
		return categories;
	}

}
