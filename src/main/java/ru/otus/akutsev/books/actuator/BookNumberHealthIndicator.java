package ru.otus.akutsev.books.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.akutsev.books.service.BookService;

@Component
public class BookNumberHealthIndicator implements HealthIndicator {

    private BookService bookService;

    @Autowired
    public BookNumberHealthIndicator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Health health() {
        Long booksNum = bookService.getCount();

        if (booksNum >= 3) {
            return Health
                    .up()
                    .status(Status.UP)
                    .withDetail("message", "There is enough books")
                    .build();
        } else {
            return Health
                    .down()
                    .status(Status.DOWN)
                    .withDetail("message", "Too few books in library")
                    .build();
        }
    }
}
