package org.java.advanced.two.module.one.bookstore;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {
    private final BookRepository bookRepository;
    private final BookDao bookDao;

    public BookService(BookRepository bookRepository, BookDao bookDao) {
        this.bookRepository = bookRepository;
        this.bookDao = bookDao;
    }

    public Book save(String title) {
        Book book = new Book(title);
        return bookRepository.save(book);
    }

    public List<Book> findAllByParams2(List<String> titles) {
        return bookRepository.findAll()
                .parallelStream()
                .filter(e -> {
                    boolean boolValue = true;
                    for (String title : titles
                    ) {
                        boolValue = boolValue && e.getTitle().toLowerCase().contains(title);
                    }
                    return boolValue;
                })
                .collect(Collectors.toUnmodifiableList())
                ;
    }

    public List<Book> findAllByParams(List<String> titles) {
        return bookDao.findBooksByParams(titles);
    }
}
