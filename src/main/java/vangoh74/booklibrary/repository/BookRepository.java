package vangoh74.booklibrary.repository;
import org.springframework.stereotype.Service;
import vangoh74.booklibrary.model.Book;

import java.util.*;

@Service
public class BookRepository {
    Map<String, Book> books = new HashMap<>();

    public Book addBook(Book book) {
        books.put(book.getIsbn(), book);
        return book;
    }

    public List<Book> getAllBooks() {
        List<Book> arrayList = new ArrayList<>();
        for (String key : books.keySet()) {
            arrayList.add(books.get(key));
        }
        return arrayList;
    }

    public Optional<Book> getBookByISBN(String isbn) {
        Optional<Book> bookOptional = Optional.ofNullable(books.get(isbn));
        return bookOptional;
    }

    public Optional<Book> deleteBook(String isbn) {
        Optional<Book> bookOptional = Optional.ofNullable(books.remove(isbn));
        return bookOptional;
    }

    public void deleteAll() {
        books.clear();
    }
}
