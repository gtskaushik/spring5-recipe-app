package guru.springframework.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = {"recipe"})
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;

	// No Cascade. So a UnitOfMeasure does not get deleted when an Ingredient is
	// deleted
	@OneToOne(fetch = FetchType.EAGER) // Eager by deafult. Just showing intent
	private UnitOfMeasure uom;

	// No Cascading here as Recipe is the owner
	@ManyToOne
	private Recipe recipe;
}
