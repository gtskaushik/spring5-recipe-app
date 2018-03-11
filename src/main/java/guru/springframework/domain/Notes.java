package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(exclude = {"recipe"})
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne // Recipe is the owner. So cascading needed. So if a Notes obj is
				// deleted, corresponding Recipe is not deleted
	private Recipe recipe;
	@Lob
	private String recipeNotes;

}
