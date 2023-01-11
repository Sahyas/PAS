package com.pas.repository;

import com.pas.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookRepository implements RepositoryInterface<Book> {

    private List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    @Override
    public void add(Book entity) {
        books.add(entity);
    }

    @Override
    public Book getById(UUID id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(Book book) {
        books.remove(book);
    }

    @Override
    public void update(Book entity) {
        books.add(entity);
    }

    @Override
    public long size() {
        return books.size();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }
}
