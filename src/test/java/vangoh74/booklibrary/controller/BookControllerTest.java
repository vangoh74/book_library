package vangoh74.booklibrary.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import vangoh74.booklibrary.model.Book;
import vangoh74.booklibrary.repository.BookRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient testClient;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void cleanUp(){
        bookRepository.deleteAll();
    }

    @Test
    public void getAllBooks() {
        //GIVEN
        Book book1 = new Book("1234", "Testtitle");
        bookRepository.addBook(book1);
        bookRepository.addBook(new Book("12", "Testtitle 2"));

        //WHEN
        List<Book> actual = testClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(Book.class)
                .returnResult()
                .getResponseBody();
        // THEN
        List<Book> expected = List.of(new Book("12", "Testtitle 2"), new Book("1234", "Testtitle"));
        assertEquals(expected, actual);
    }
    @Test
    void addBook() {
        //GIVEN
        Book book = new Book("1234", "test-Title");
        //WHEN
        Book actual = testClient.post()
                .uri("http://localhost:" + port + "/book")
                .bodyValue(book)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Book.class)
                .returnResult()
                .getResponseBody();

        //THEN
        Book expected = new Book("1234", "test-Title");
        assertEquals(expected, actual);
    }

    @Test
    void gebBookByISBN_whenInvalidIsbn_thenThrowException() {
        // GIVEN
        Book book = new Book("12", "test-Title");

        //WHEN
        testClient.get()
                .uri("http://localhost:" + port + "/book")
                .exchange()
                .expectStatus().is5xxServerError();
        // THEN
    }

    @Test
    void deleteBook() {
    }
}