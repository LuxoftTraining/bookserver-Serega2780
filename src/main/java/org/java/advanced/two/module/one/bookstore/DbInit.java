package org.java.advanced.two.module.one.bookstore;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DbInit {
    private final Faker faker = Faker.instance();
    private final BookRepository bookRepository;

    public DbInit(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    private void postConstruct() {

        for (int i = 0; i < 100_000; i++) {
            Book book = new Book(faker.book().title().concat(", ").concat(faker.book().author()));
            bookRepository.save(book);
        }
        List<Book> books = bookRepository.findAll();
        System.out.println("A book quantity is " + books.size());
    }
}
