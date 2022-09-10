package fi.haagahelia.coolstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.coolstore.domain.Book;
import fi.haagahelia.coolstore.domain.BookRepository;
import fi.haagahelia.coolstore.domain.CategoryRepository;

// NOTICE: id refers to the id in the book class

@Controller
public class BookController {

	@Autowired
	private BookRepository brepository;

	@Autowired
	private CategoryRepository crepository;

	// Login page
	@RequestMapping(value = "/login")
	public String showLogin() {
		return "login";
	}

	// REST Methods
	// Get all books in JSON
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> studentListRest() {
		return (List<Book>) brepository.findAll();
	}

	// Get book by id in JSON
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return brepository.findById(bookId);
	}

	// HTML Methods
	@GetMapping(value = { "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/add")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(Book book) {
		brepository.save(book);
		return "redirect:booklist";
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		brepository.deleteById(id);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/edit/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", brepository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
}