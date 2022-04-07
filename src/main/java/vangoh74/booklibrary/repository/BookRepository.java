package vangoh74.booklibrary.repository;
import org.springframework.stereotype.Service;
import vangoh74.booklibrary.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Book getBookByISBN(String isbn) {
        return books.get(isbn);
    }

    public Book deleteBook(String isbn) {
        return books.remove(isbn);
    }

    public void deleteAll() {
        books.clear();
    }
}
