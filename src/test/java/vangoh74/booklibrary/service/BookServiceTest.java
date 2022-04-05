package vangoh74.booklibrary.service;

import org.junit.jupiter.api.Test;
import vangoh74.booklibrary.model.Book;
import vangoh74.booklibrary.repository.BookRepository;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {
    private BookRepository bookRepo = mock(BookRepository.class);
    private BookService bookService = new BookService(bookRepo);

    @Test
    void addBook_whenISBN3426633094AndTitleDieTherapie_thenReturnBookWithSameISBNandTitle() {
        // GIVEN
        when(bookRepo.addBook(new Book("3426633094", "Die Therapie"))).thenReturn(new Book("3426633094", "Die Therapie"));

        // GIVEN / ACTUAL
        bookService.addBook(new Book("3426633094", "Die Therapie"));
        verify(bookRepo).addBook(new Book("3426633094", "Die Therapie"));
    }

    @Test
    void getAllBooks_whenListWithBooks_thenReturnListWithBooks() {
        // GIVEN
        List<Book> books = asList(
                new Book("12345", "O Alquimista"),
                new Book("4488599", "Die Leiden des jungen Werthers"),
                new Book("359045298", "Meu Pé de Laranja Lima"));

        when(bookRepo.getAllBooks()).thenReturn(books);

        // WHEN
        List<Book> expected = asList(
                new Book("12345", "O Alquimista"),
                new Book("4488599", "Die Leiden des jungen Werthers"),
                new Book("359045298", "Meu Pé de Laranja Lima")
        );

        // THEN
        List<Book> actual = bookService.getAllBooks();

        verify(bookRepo).getAllBooks();
        assertEquals(expected, actual);
    }

    @Test
    void getBookByISBN_whenISBN3426633094_thenReturnBookWithISBN3426633094() {
        // GIVEN
        when(bookRepo.getBookByISBN("3426633094")).thenReturn(new Book("3426633094", "Die Therapie"));

        //WHEN
        Book actual = bookService.getBookByISBN("3426633094");

        //THEN
        Book expected = new Book("3426633094", "Die Therapie");

        verify(bookRepo).getBookByISBN("3426633094");
        assertEquals(expected, actual);
    }

    @Test
    void deleteBook() {
        // GIVEN
        when(bookRepo.deleteBook("1244214325")).thenReturn(new Book("1244214325", "Vom Winde verweht"));

        // WHEN
        Book actual = bookService.deleteBook("1244214325");

        // THEN
        Book expected = new Book("1244214325", "Vom Winde verweht");

        verify(bookRepo).deleteBook("1244214325");
        assertEquals(expected, actual);
    }
}