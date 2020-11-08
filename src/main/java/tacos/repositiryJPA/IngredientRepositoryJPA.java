package tacos.repositiryJPA;

import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

public interface IngredientRepositoryJPA extends CrudRepository<Ingredient, String> {
}
