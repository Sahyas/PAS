package com.pas.controller;

import com.pas.model.Book;
import com.pas.service.impl.BookService;

import java.util.List;
import java.util.UUID;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/books")
@ApplicationScoped
public class BookController {
    @Inject
    private BookService bookService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public String saveBook(Book book) {
        book.setId(UUID.randomUUID());
        book.setRented(false);
        bookService.registerBook(book);
        return book.toString();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{bookId}")
    public Book getBook(@PathParam("bookId") UUID bookId) {
        return bookService.getBookById(bookId);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getBooks() {
        return bookService.findAllBooks();
    }

    @DELETE
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{bookId}")
    public void deleteBook(@PathParam("bookId") UUID bookId) {
        bookService.unregisterBook(bookService.getBookById(bookId));
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book updateBook(Book book) {
        return bookService.updateBook(book);
    }
}
