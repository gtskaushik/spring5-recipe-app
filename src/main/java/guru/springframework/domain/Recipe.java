package guru.springframework.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Asks the DB to do it
	private Long id;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
	private Integer servings;
	private String source;
	private String url;
	private String directions;
	// TODO add
	// private Difficulty difficulty;

	@Lob // Blob in database
	private Byte[] image;

	// Recipe is the owner here. If Recipe is deleted, corresponding Notes is
	// also deleted
	@OneToOne(cascade = CascadeType.ALL)
	private Notes notes;

	// This means that Ingredient will have the key with recipe stored in recipe
	// column
	// TODO Check if this is the case
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients;
}
