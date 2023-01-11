package com.pas.service;

import com.pas.model.Book;
import com.pas.model.ClientType;
import com.pas.repository.BookRepository;

import java.util.List;
import java.util.UUID;

public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getBookById(UUID id) {
        return bookRepository.getById(id);
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Book registerBook(String title, String author, String serialNumber, String genre) {
        Book book = new Book(UUID.randomUUID(), title, author, serialNumber, genre, false);
        bookRepository.add(book);
        return book;
    }

    public void unregisterBook(Book book) {
        bookRepository.delete(book);
    }

    public void updateBook(Book book) {
        bookRepository.update(book);
    }
}
