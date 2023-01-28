package com.pas.controller;

import com.pas.model.Book;
import com.pas.service.impl.BookService;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/books")
@ApplicationScoped
public class BookController {
    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Inject
    private BookService bookService;

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveBook(Book book) {
        Set<ConstraintViolation<Book>> violation = validator.validate(book);
        List<String> errors = violation.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
        if (!violation.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(errors).build();
        }
        book.setId(UUID.randomUUID());
        book.setRented(false);
        bookService.registerBook(book);
        return Response.status(Response.Status.CREATED).build();
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
    @RolesAllowed({"Admin"})
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/{bookId}")
    public Response deleteBook(@PathParam("bookId") UUID bookId) {
        if (bookService.unregisterBook(bookService.getBookById(bookId))) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity("Book with id " + bookId + " is currently rented").build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Book updateBook(Book book) {
        return bookService.updateBook(book);
    }
}
