package com.pas.service;

import com.pas.model.Book;
import com.pas.model.Client;
import com.pas.model.Rent;
import com.pas.repository.RentRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

public class RentService {
        private final RentRepository rentRepository;
        private final ClientService clientService;
        private final BookService bookService;

    public RentService(ClientService clientService,
                BookService bookService,
                RentRepository rentRepository) {
            this.rentRepository = rentRepository;
            this.clientService = clientService;
            this.bookService = bookService;
        }

        public void rentBook(UUID clientId, UUID bookId) {
        Client client = clientService.getClientById(clientId);
        Book book = bookService.getBookById(bookId);
        Rent rent = new Rent(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now().plus(10, ChronoUnit.DAYS), book, client);
        if (!book.isRented()) {
            book.setRented(true);
            bookService.updateBook(book);
            rentRepository.add(rent);
        }
    }

    public boolean returnBook(Book book) {
        if(book.isRented()) {
            Book foundBook = bookService.getBookById(book.getId());
            Rent rent = getRentByBook(book);
            rentRepository.delete(rent);
            foundBook.setRented(false);
            bookService.updateBook(book);
            return true;
        } else {
            System.out.println("Ta książka nie jest wypozyczona");
            return false;
        }
    }

    public List<Rent> findAllCurrentRents() {
        return rentRepository.findAll();
    }

    public Rent getRentByBook(Book book) {
        List<Rent> rents = rentRepository.findAll();
        return rents.stream()
                .filter(rent -> rent.getBook().getId().equals(book.getId()))
                .findFirst().orElse(null);
    }

    public Rent getRentByClient(Client client) {
        List<Rent> rents = rentRepository.findAll();
        return rents.stream()
                .filter(rent -> rent.getClient().getId().equals(client.getId()))
                .findFirst().orElse(null);
    }
}
