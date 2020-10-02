package ru.otus.akutsev.books.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import ru.otus.akutsev.books.model.Author;
import ru.otus.akutsev.books.model.Book;
import ru.otus.akutsev.books.model.Genre;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Optional;

@Repository
//@RepositoryRestResource(path = "api")
public interface BookDao extends JpaRepository<Book, Long> {
	Book save(Book book);

	Optional<Book> findAById(long id);

	@EntityGraph(attributePaths = {"author", "genre"})
	@Override
	//@RestResource(path = "books", rel = "books")
	List<Book> findAll();

	void delete(Book book);
}
