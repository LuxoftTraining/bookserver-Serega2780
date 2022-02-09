package org.java.advanced.two.module.one.bookstore;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("{title}")
    public ResponseEntity<Book> save(@PathVariable("title") String title) {
        return ResponseEntity.ok(bookService.save(title));
    }

    @GetMapping("{title_path}")
    public ResponseEntity<List<Book>> findAllByParams(@PathVariable("title_path")
                                                              String titlePath) {
        List<String> titles = List.of(titlePath.toLowerCase().split(" "));
        return ResponseEntity.ok(bookService.findAllByParams(titles));
    }
}
