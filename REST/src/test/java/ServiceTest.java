//import com.pas.model.Client;
//import com.pas.model.ClientType;
//import com.pas.model.Rent;
//import com.pas.repository.BookRepository;
//import com.pas.repository.ClientRepository;
//import com.pas.repository.RentRepository;
//import com.pas.service.impl.BookServiceImpl;
//import com.pas.service.impl.ClientServiceImpl;
//import com.pas.service.impl.RentServiceImpl;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ServiceTest {
//    private ClientRepository clientRepository;
//    private BookRepository bookRepository;
//    private RentRepository rentRepository;
//
//    private ClientServiceImpl clientService;
//    private BookServiceImpl bookService;
//    private RentServiceImpl rentServiceImpl;
//
//    @BeforeEach
//    void beforeEach() {
//        clientRepository = new ClientRepository();
//
//        bookRepository = new BookRepository();
//
//        rentRepository = new RentRepository();
//
//        this.clientService = new ClientServiceImpl(clientRepository);
//        this.bookService = new BookServiceImpl(bookRepository);
//        this.rentServiceImpl = new RentServiceImpl(clientService, bookService, rentRepository);
//    }
//
//    @org.junit.jupiter.api.Test
//    void rentRentedBookTest() {
//        List<Rent> rents = new ArrayList<>();
//        Client client = clientService.addClient("Szymon", "Zakrzewski", "0123", 2,21, ClientType.ADMIN);
//        Book book = bookService.registerBook(new Book("someTitle", "someAuthor", "123", "someGenre"));
//        rentServiceImpl.rentBook(client.getId(), book.getId());
//        Rent rent = rentServiceImpl.getRentByBook(book);
//        rentServiceImpl.rentBook(client.getId(), book.getId());
//        rents.addAll(rentServiceImpl.findAllCurrentRents());
//        Assertions.assertEquals(rents.size(), 1);
//    }
//
//    @org.junit.jupiter.api.Test
//    void returnBookTest() {
//        List<Rent> rents = new ArrayList<>();
//        Client client = clientService.addClient("Szymon", "Zakrzewski", "0123", 2,  21, ClientType.ADMIN);
//        Book book = bookService.registerBook(new Book("someTitle", "someAuthor", "123", "someGenre"));
//        rentServiceImpl.rentBook(client.getId(), book.getId());
//        Rent rent = rentServiceImpl.getRentByBook(book);
//        rents.addAll(rentServiceImpl.findAllCurrentRents());
//        Assertions.assertEquals(rents.size(), 1);
//
//        book = bookService.getBookById(book.getId());
//        rentServiceImpl.returnBook(book);
//        rents.removeAll(rents);
//        rents.addAll(rentServiceImpl.findAllCurrentRents());
//        Assertions.assertEquals(rents.size(), 0);
//    }
//}
