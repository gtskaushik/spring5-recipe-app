
package guru.springframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.services.RecipeService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class IndexController {

	RecipeService recipeService;

	@RequestMapping({ "", "/", "/index" })
	public String getIndexPage(Model model) {

		model.addAttribute("recipes", recipeService.getRecipes());
		return "index";
	}
}