package com.pas.controller;


import com.pas.model.Book;
import com.pas.service.RestClient;

import java.util.Collections;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.ws.rs.core.MediaType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Named
@ApplicationScoped
@Getter
@Setter
@Slf4j
public class BookBean {
    private List<Book> books;
    private Book book;
    private RestClient restClient = new RestClient();
    private int statusCode;
    private String filter;

    public BookBean() {
    }

    public List<Book> getBooks() {
        this.books = restClient.get("/books").readEntity(List.class);
        return books;
    }

    public void deleteBook(String id) {
        restClient.delete("/books/" + id);
    }

    public void addBook(Book book) {
        statusCode = restClient.post("/books", book, Collections.EMPTY_MAP, MediaType.APPLICATION_JSON_TYPE).getStatus();
    }

    public String editBook(Book book) {
        String value = "";
        if (restClient.put("/books", book).getStatus() == 200) {
            value = "booksPage";
        }
        this.book = new Book();
        return value;
    }

    public String editBookSetter(String id) {
        this.book = restClient.get("/books/" + id).readEntity(Book.class);
        return "bookEdit";
    }

    public int getStatusCode() {
        return statusCode;
    }
}
