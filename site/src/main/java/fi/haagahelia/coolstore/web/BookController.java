package fi.haagahelia.coolstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fi.haagahelia.coolstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;

//	@GetMapping("/booklist")
	@RequestMapping(value = { "/", "/booklist" })
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "booklist";
	}
}