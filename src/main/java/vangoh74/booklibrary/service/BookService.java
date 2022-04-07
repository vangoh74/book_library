package vangoh74.booklibrary.service;
import org.springframework.stereotype.Service;
import vangoh74.booklibrary.model.Book;
import vangoh74.booklibrary.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<Book> optBook = bookRepo.getBookByISBN(isbn);
        if (optBook.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optBook.get();
        //return bookRepo.getBookByISBN(isbn)
            //    .orElseThrow(() -> new NoSuchElementException("Book with isbn " + isbn + " is not found!"));
    }

    public Book deleteBook(String isbn) {
       /*
        Optional<Book> optBook = bookRepo.deleteBook(isbn);
        if (optBook.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optBook.get();*/
        return bookRepo.deleteBook(isbn)
                .orElseThrow(() -> new NoSuchElementException("Book with isbn " + isbn + " is not found!"));
    }
}
