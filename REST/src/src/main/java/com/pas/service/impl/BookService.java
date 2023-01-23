package src.main.java.com.pas.service.impl;


import src.main.java.com.pas.model.Book;
import src.main.java.com.pas.repository.BookRepository;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class BookService {
    @Inject
    private BookRepository bookRepository;

    public Book getBookById(UUID id) {
        log.info("retrieving book with id " + id);
        return bookRepository.getById(id);
    }

    public List<Book> findAllBooks() {
        log.info("retrieving all books");
        return bookRepository.findAll();
    }

    public Book registerBook(Book book) {
        log.info("adding book: " + book);
        bookRepository.add(book);
        return book;
    }

    public boolean unregisterBook(Book book) {
        if (!book.isRented()) {
            log.info("unregistering book" + book);
            bookRepository.delete(book);
            return true;
        } else {
            log.warn("cannot unregister rented book");
            return false;
        }
    }

    public Book updateBook(Book book) {
        log.info("updating book: " + book);
        return bookRepository.update(book);
    }
}
