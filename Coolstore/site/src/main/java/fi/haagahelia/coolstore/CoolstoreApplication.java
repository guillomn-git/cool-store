package fi.haagahelia.coolstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.coolstore.domain.Book;
import fi.haagahelia.coolstore.domain.BookRepository;

@SpringBootApplication
public class CoolstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(CoolstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoolstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			log.info("Save books");
			repository.save(new Book("Pride and Prejudice", "Jane Austen", "9780679783268", 7.99f));
			repository.save(new Book("1984", "George Orwell", "9780452284234", 12.39f));
		};
	}

}
