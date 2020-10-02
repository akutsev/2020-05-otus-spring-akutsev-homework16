package ru.otus.akutsev.books.integrationtests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.akutsev.books.controller.BooksPagesController;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Genre;
import ru.otus.akutsev.books.service.AuthorService;
import ru.otus.akutsev.books.service.BookService;
import ru.otus.akutsev.books.service.GenreService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Тест простого контроллера")
@WebMvcTest(BooksPagesController.class)
public class BookPagesControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private BookService bookService;
	@MockBean
	private AuthorService authorService;
	@MockBean
	private GenreService genreService;

	@Test
	public void saveNewBookPostTes() throws Exception {
		Book book2 = new Book(2, "Crime amd Punishment", null, null);

		mockMvc.perform(post("/save")
				.content(objectMapper.writeValueAsString(book2)))
				.andExpect(redirectedUrl("/"));
	}

	@Test
	public void saveNewBookGetTest() throws Exception {
		this.mockMvc.perform(get("/save"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Сохранить")));
	}

	@Test
	public void editBookGetTest() throws Exception {
		Author author = new Author(75, "Ilf and Petrov");
		Genre genre = new Genre(89, "Comedy");
		Book book = new Book(1, "12 chairs", author, genre);

		given(bookService.getAById(1)).willReturn(Optional.of(book));

		this.mockMvc.perform(get("/edit?id=1"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("12 chairs")));
	}

	@Test
	public void showAllBooksGetTest() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(content().string(containsString("Имя книги")))
				.andExpect(content().string(containsString("Имя автора книги")))
				.andExpect(content().string(containsString("азвание жанра")));
	}
}
