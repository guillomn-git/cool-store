package fi.haagahelia.coolstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.haagahelia.coolstore.domain.Book;
import fi.haagahelia.coolstore.domain.BookRepository;
import fi.haagahelia.coolstore.domain.Category;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository brepository;

	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = brepository.findByTitle("1984");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("George Orwell");
	}

	@Test
	public void createNewBook() {
		Book book = new Book("Title", "Author", "ISBN", 69.00f, new Category("Test"));
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void deleteNewBook() {
		List<Book> books = brepository.findByTitle("1984");
		Book book = books.get(0);
		brepository.delete(book);

		List<Book> newBooks = brepository.findByTitle("1984");
		assertThat(newBooks).hasSize(0);
	}
}