package ru.otus.akutsev.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Genre;
import ru.otus.akutsev.books.service.AuthorService;
import ru.otus.akutsev.books.service.BookService;
import ru.otus.akutsev.books.service.GenreService;

import java.util.List;

@Controller
public class BooksPagesController {

	private final BookService bookService;
	private final AuthorService authorService;
	private final GenreService genreService;

	@Autowired
	public BooksPagesController(BookService bookService, AuthorService authorService, GenreService genreService) {
		this.bookService = bookService;
		this.authorService = authorService;
		this.genreService = genreService;
	}

	@GetMapping("/")
	public String listPage() {
		return "allBooks";
	}

	@GetMapping("/save")
	public String saveEditedBook(Model model) {
		List<Author> authors = authorService.getAll();
		List<Genre> genres = genreService.findAll();

		model.addAttribute("authors", authors);
		model.addAttribute("genres", genres);

		return "save";
	}

	@GetMapping("/edit")
	public String editPage(@RequestParam("id") long id, Model model) {
		Book book = bookService.getAById(id).orElseThrow(NoSuchBookException::new);
		List<Author> authors = authorService.getAll();
		List<Genre> genres = genreService.findAll();

		model.addAttribute("book", book);
		model.addAttribute("authors", authors);
		model.addAttribute("genres", genres);

		return "edit";
	}

	@PostMapping("/edit")
	public String saveEditedBook(Book book) {
		bookService.save(book);

		return "redirect:/";
	}

	@PostMapping("/delete")
	public String deleteBook(long id) {
		bookService.deleteById(id);

		return "redirect:/";
	}

	@PostMapping("/save")
	public String saveNewBook(Book book) {
		bookService.save(book);

		return "redirect:/";
	}
}
