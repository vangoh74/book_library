package vangoh74.booklibrary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vangoh74.booklibrary.model.Book;
import vangoh74.booklibrary.service.BookService;

import java.util.List;

@RestController
@RequestMapping("book") //http:localhost:8080/book
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping(path = "{isbn}")
    public Book gebBookByISBN(@PathVariable String isbn) {
        return bookService.getBookByISBN(isbn);
    }

    @DeleteMapping(path = "{isbn}")
    public Book deleteBook(@PathVariable String isbn) {
        return bookService.deleteBook(isbn);
    }
}
