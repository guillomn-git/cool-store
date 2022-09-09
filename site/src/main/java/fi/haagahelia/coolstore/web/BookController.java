package fi.haagahelia.coolstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

//	@GetMapping("/booklist")
	@RequestMapping(value = { "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "booklist";
	}

	@RequestMapping(value = "/add")
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
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		brepository.deleteById(id);
		return "redirect:../booklist";
	}

	@RequestMapping(value = "/edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", brepository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}
}