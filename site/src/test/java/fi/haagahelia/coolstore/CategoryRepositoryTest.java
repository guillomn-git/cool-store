package fi.haagahelia.coolstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.coolstore.domain.Category;
import fi.haagahelia.coolstore.domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository crepository;

	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = crepository.findByName("Romance");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo("Romance");
	}

	@Test
	public void createNewCategory() {
		Category category = new Category("Test");
		crepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}

	@Test
	public void deleteNewCategory() {
		List<Category> categories = crepository.findByName("Romance");
		Category category = categories.get(0);
		crepository.delete(category);
		List<Category> newCategories = crepository.findByName("Romance");
		assertThat(newCategories).hasSize(0);
	}
}