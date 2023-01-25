package com.pas.repository;


import com.pas.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class BookRepository implements RepositoryInterface<Book> {

    private List<Book> books = Collections.synchronizedList(new ArrayList<>());

    @Override
    public synchronized void add(Book entity) {
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
    public synchronized void delete(Book book) {
        books.remove(book);
    }

    @Override
    public synchronized Book update(Book entity) {
        Book foundBook = books.stream()
                .filter(book -> book.getId().equals(entity.getId()))
                .findFirst()
                .orElse(null);
        if (foundBook != null) {
            int index = books.indexOf(foundBook);
            books.set(index, entity);
            return entity;
        } else {
            log.warn("object " + entity + " do not exist");
            return null;
        }
    }

    @Override
    public long size() {
        return books.size();
    }

    @Override
    public List<Book> findAll() {
        return books;
    }

    @PostConstruct
    public void init() {
        Book book = new Book("title", "autohr", "123", "genre");
        Book book2 = new Book("title2", "autohr2", "1232", "genre2");
        Book book3 = new Book("title3", "autohr3", "1233", "genre3");

        this.add(book);
        this.add(book2);
        this.add(book3);
    }
}
