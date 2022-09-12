package fi.haagahelia.coolstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.coolstore.domain.Book;
import fi.haagahelia.coolstore.domain.BookRepository;
import fi.haagahelia.coolstore.domain.Category;
import fi.haagahelia.coolstore.domain.CategoryRepository;
import fi.haagahelia.coolstore.domain.User;
import fi.haagahelia.coolstore.domain.UserRepository;

@SpringBootApplication
public class CoolstoreApplication {

	@Autowired
	private UserRepository urepository;

	private static final Logger log = LoggerFactory.getLogger(CoolstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoolstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("Save books");
			crepository.save(new Category("Romance"));
			crepository.save(new Category("Political Fiction"));
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Science Fiction"));
			crepository.save(new Category("Mystery"));
			crepository.save(new Category("Action & Adventure"));
			crepository.save(new Category("Horror"));
			crepository.save(new Category("Children's"));
			crepository.save(new Category("Undefined"));

			brepository.save(new Book("Pride and Prejudice", "Jane Austen", "9780679783268", 7.99f,
					crepository.findByName("Romance").get(0)));
			brepository.save(new Book("1984", "George Orwell", "9780452284234", 12.39f,
					crepository.findByName("Political Fiction").get(0)));

			new User("user", "$2a$10$o9EaMx2mzHxMY64EeNnHJurqTfBMc.42Yd7PYroTylKMloHqJtXoe", "user@email.com", "USER");
			urepository.save(urepository.findByUsername("user"));

			new User("admin", "$2a$10$1NcT5maAHowCWuIlMa98peItBu5EDMgV5cljf8jJr8BM9aPQ5KBbu", "admin@email.com",
					"ADMIN");

			urepository.save(urepository.findByUsername("admin"));

		};
	}

}