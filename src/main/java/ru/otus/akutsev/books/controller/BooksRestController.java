package ru.otus.akutsev.books.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.service.BookService;

import java.util.List;

@RestController
public class BooksRestController {

    private final BookService bookService;

    @Autowired
    public BooksRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/api/books")
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }
}