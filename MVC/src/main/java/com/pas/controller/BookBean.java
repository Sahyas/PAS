package com.pas.controller;


import com.pas.model.Book;
import com.pas.service.RestClient;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Named
@RequestScoped
@Getter
@Setter
@Slf4j
public class BookBean {
    private List<Book> books;
    private Book book = new Book();
    private RestClient restClient = new RestClient();
    private int statusCode;

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
        statusCode = restClient.post("/books", book).getStatus();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
