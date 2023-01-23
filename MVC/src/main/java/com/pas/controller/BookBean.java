package com.pas.controller;


import com.pas.model.Book;
import com.pas.service.RestClient;

import java.util.List;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class BookBean {
    private List<Book> books;
    private RestClient restClient = new RestClient();

    public BookBean() {
    }

    public List<Book> getBooks() {
        this.books = restClient.get("/books").readEntity(List.class);
        return books;
    }

    public void deleteBook(String id) {
        restClient.delete("/books/" + id);
    }
}
