import com.pas.model.Book;
import com.pas.model.Client;
import com.pas.model.ClientType;
import com.pas.model.Rent;
import com.pas.repository.BookRepository;
import com.pas.repository.ClientRepository;
import com.pas.repository.RentRepository;
import com.pas.service.BookService;
import com.pas.service.ClientService;
import com.pas.service.RentService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class ServiceTest {
    private ClientRepository clientRepository;
    private BookRepository bookRepository;
    private RentRepository rentRepository;

    private ClientService clientService;
    private BookService bookService;
    private RentService rentService;

    @BeforeEach
    void beforeEach() {
        clientRepository = new ClientRepository();

        bookRepository = new BookRepository();

        rentRepository = new RentRepository();

        this.clientService = new ClientService(clientRepository);
        this.bookService = new BookService(bookRepository);
        this.rentService = new RentService(clientService, bookService, rentRepository);
    }

    @org.junit.jupiter.api.Test
    void rentRentedBookTest() {
        List<Rent> rents = new ArrayList<>();
        Client client = clientService.addClient("Szymon", "Zakrzewski", "0123", 2,21, ClientType.ADMIN);
        Book book = bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        rentService.rentBook(client.getId(), book.getId());
        Rent rent = rentService.getRentByBook(book);
        rentService.rentBook(client.getId(), book.getId());
        rents.addAll(rentService.findAllCurrentRents());
        Assertions.assertEquals(rents.size(), 1);
    }

    @org.junit.jupiter.api.Test
    void returnBookTest() {
        List<Rent> rents = new ArrayList<>();
        Client client = clientService.addClient("Szymon", "Zakrzewski", "0123", 2,  21, ClientType.ADMIN);
        Book book = bookService.registerBook("someTitle", "someAuthor", "123", "someGenre");
        rentService.rentBook(client.getId(), book.getId());
        Rent rent = rentService.getRentByBook(book);
        rents.addAll(rentService.findAllCurrentRents());
        Assertions.assertEquals(rents.size(), 1);

        book = bookService.getBookById(book.getId());
        rentService.returnBook(book);
        rents.removeAll(rents);
        rents.addAll(rentService.findAllCurrentRents());
        Assertions.assertEquals(rents.size(), 0);
    }
}
