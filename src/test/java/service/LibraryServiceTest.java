package service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Author;
import model.Book;
import model.Category;
import model.Loan;
import model.Member;
import repository.BookRepository;
import repository.LibrarianRepository;
import repository.LoanRepository;
import repository.UserRepository;
import util.IdGenerator;

public class LibraryServiceTest {

    private LibraryService libraryService;
    private BookRepository bookRepo;
    private UserRepository userRepo;
    private LoanRepository loanRepository;
    private BillingService billingService;
    private NotificationService notificationService;
    private LibrarianRepository librarianRepository;



    @BeforeEach
    void setUp() {
        bookRepo = new BookRepository();
        userRepo = new UserRepository();
        loanRepository = new LoanRepository();
        billingService = new BillingService();
        notificationService = new NotificationService();
        librarianRepository = new LibrarianRepository();
        libraryService = new LibraryService(bookRepo, userRepo, loanRepository,
            billingService, notificationService, librarianRepository);


    }

    @Test
    void testAddBook() {
        Author author = new Author(1, "George Orwell");
        Category category = new Category(1, "Dystopian");
        Book book = libraryService.addBook("1984", author, category, 15.0, "9780451524935");
        assert book != null;
        assert book.getTitle().equals("1984");
        assert book.getPrice() == 15.0;
        assert book.getIsbn().equals("9780451524935");
    }

    @Test
    void testBorrowBook(){
        Author a1 = new Author(1, "Yalçın Küçük");
        Category c1 = new Category(1, "Araştırma");
        Book book = new Book(IdGenerator.nextId(),"The Great Gatsby", a1, c1, 10.99, "9780743273565");

        Member nm = libraryService.addMember("John Doe", "john@example.com", 12345678901L);
        userRepo.add(nm);
        nm.borrowBook(book.getId());
        Loan loan = new Loan(IdGenerator.nextId(), book.getId(), nm.getId(), LocalDate.now() , LocalDate.now().plusDays(14), book.getPrice() * 0.1);
        loanRepository.addActive(loan);
        String result = libraryService.borrowBook(nm.getId(), book.getId());


        assertTrue(book.isAvailable());
        assertEquals(1, nm.getBorrowedBookIds().size());
        assertEquals(1, loanRepository.getAllActive().size());

    }

}
