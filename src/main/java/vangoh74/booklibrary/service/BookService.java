package vangoh74.booklibrary.service;
import org.springframework.stereotype.Service;
import vangoh74.booklibrary.model.Book;
import vangoh74.booklibrary.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    BookRepository bookRepo;

    BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    public Book addBook(Book book) {
        return bookRepo.addBook(book);
    }

    public List<Book> getAllBooks() {
        return bookRepo.getAllBooks();
    }

    public Book getBookByISBN(String isbn) {
        return bookRepo.getBookByISBN(isbn);
    }

    public Book deleteBook(String isbn) {
        return bookRepo.deleteBook(isbn);
    }

}
