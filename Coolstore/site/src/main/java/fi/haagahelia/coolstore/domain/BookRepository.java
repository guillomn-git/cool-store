package fi.haagahelia.coolstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
	List<Book> findAll();

	List<Book> findByTitle(String title);
}