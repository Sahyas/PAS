package com.pas.service.impl;

import com.pas.model.Book;
import com.pas.model.Rent;
import com.pas.model.User;
import com.pas.repository.BookRepository;
import com.pas.repository.RentRepository;
import com.pas.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class RentService {
    @Inject
    private RentRepository rentRepository;
    @Inject
    private UserRepository userRepository;
    @Inject
    private BookRepository bookRepository;

    public Rent rentBook(UUID clientId, UUID bookId) {
        Book book = bookRepository.getById(bookId);
        User user = userRepository.getById(clientId);
        Rent rent = new Rent(UUID.randomUUID(), LocalDateTime.now(), null, book, user);
        if (!book.isRented() && user.isActive()) {
            book.setRented(true);
            bookRepository.update(book);
            rentRepository.add(rent);
            return rent;
        }
        return null;
    }

    public Rent returnBook(UUID bookId) {
        Book book = bookRepository.getById(bookId);
        if (book.isRented()) {
            Book foundBook = bookRepository.getById(book.getId());
            Rent rent = getRentByBook(book);
            rent.setEndTime(LocalDateTime.now());
            rentRepository.update(rent);
            foundBook.setRented(false);
            bookRepository.update(book);
            return rent;
        } else {
            log.warn("książka: " + book + " nie jest wypozyczona");
            return null;
        }
    }

    public List<Rent> findAllCurrentRents() {
        List<Rent> rents = rentRepository.findAll();
        return rents.stream()
                .filter(rent -> rent.getEndTime() == null)
                .collect(Collectors.toList());
    }

    public List<Rent> findAllRents() {
        return rentRepository.findAll();
    }

    public Rent getRentByBook(Book book) {
        List<Rent> rents = rentRepository.findAll();
        return rents.stream()
                .filter(rent -> rent.getBook().getId().equals(book.getId()))
                .findFirst().orElse(null);
    }

    public Rent getRentByClient(User user) {
        List<Rent> rents = rentRepository.findAll();
        return rents.stream()
                .filter(rent -> rent.getUser().getId().equals(user.getId()))
                .findFirst().orElse(null);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Rent getRentById(UUID rentId) {
        return rentRepository.getById(rentId);
    }

    public void deleteRent(Rent rent) {
        if (rent.getEndTime() != null) {
            rentRepository.delete(rent);
        }
    }

    public Rent updateRent(Rent rent) {
        return rentRepository.update(rent);
    }
}
