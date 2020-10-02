package ru.otus.akutsev.books.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.akutsev.books.dao.BookDao;
import ru.otus.akutsev.books.dao.CommentDao;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Comment;
import ru.otus.akutsev.books.model.Genre;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

	private final BookDao bookDao;
	private final CommentDao commentDao;

	@Autowired
	public BookServiceImpl(BookDao bookDao, CommentDao commentDao) {
		this.bookDao = bookDao;
		this.commentDao = commentDao;
	}

	@Override
	@Transactional
	public Book save(Book book) {
		return bookDao.save(book);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Book> getAById(long id) {
		return bookDao.findAById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getAll() {
		return bookDao.findAll();
	}

	@Override
	public Long getCount() {
		return bookDao.count();
	}

	@Override
	@Transactional
	public void updateBook (Book book, String newName, Author newAuthor, Genre newGenre) {
		book.setAuthor(newAuthor);
		book.setGenre(newGenre);
		book.setName(newName);

		bookDao.save(book);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Comment> getAllComments(Book book) {
		return commentDao.findByBook(book);
	}

	@Override
	@Transactional
	public void delete (Book book) {
		bookDao.delete(book);
	}

	@Override
	@Transactional
	public void deleteById (long id) {
		bookDao.deleteById(id);
	}
}
