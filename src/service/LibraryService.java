package service;

import java.time.LocalDate;
import java.util.List;

import model.Author;
import model.Book;
import model.Category;
import model.Invoice;
import model.Loan;
import model.Member;
import repository.BookRepository;
import repository.LoanRepository;
import repository.UserRepository;
import util.IdGenerator;

public class LibraryService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final BillingService billingService;
    private final NotificationService notificationService;

    public LibraryService(BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository,
            BillingService billingService, NotificationService notificationService) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.billingService = billingService;
        this.notificationService = notificationService;
    }

    public Book addBook(String title, Author author, Category cat, double price) {
        int id = IdGenerator.nextId();
        Book book = new Book(id, title, author, cat, price);
        bookRepository.addBook(book);
        return book;
    }

    public boolean updateBook(int id, String title, Author author, Category cat, double price) {
        Book b = bookRepository.getById(id);
        if (b == null)
            return false;
        b.setTitle(title);
        b.setAuthor(author);
        b.setCategory(cat);
        b.setPrice(price);
        return true;
    }

    public boolean removeBook(int id) {
        Book b = bookRepository.getById(id);
        if (b == null)
            return false;
        if (!b.isAvailable())
            return false;
        bookRepository.remove(id);
        return true;
    }

    public List<Book> searchByTitle(String q) {
        return bookRepository.findByTitle(q);
    }

    public List<Book> searchByAuthor(String authorName) {
        return bookRepository.findByAuthor(authorName);
    }

    public List<Book> searchByCategory(String categoryName) {
        return bookRepository.findByCategory(categoryName);
    }

    public Book getBookById(int id) {
        return bookRepository.getById(id);
    }

    public Member addMember(String name, String email) {
        int id = IdGenerator.nextId();
        Member member = new Member(id, name, email);
        userRepository.add(member);
        return member;
    }

    public String borrowBook(int memberId, int bookId) {
        Member member = userRepository.getById(memberId);
        Book book = bookRepository.getById(bookId);
        if (member == null)
            return "Üye bulunamadı";
        if (book == null)
            return "Kitap bulunamadı";
        if (!book.isAvailable())
            return "Kitap zaten ödünç verilmiş.";
        if (!member.canBorrow())
            return "Ödünç limitine ulaşıldı (max " + Member.BORROW_LIMIT + ").";

        LocalDate loanDate = LocalDate.now();
        LocalDate dueDate = loanDate.plusDays(14);
        double fee = book.getPrice() * 0.1;
        int loanId = IdGenerator.nextId();
        Loan loan = new Loan(loanId, bookId, memberId, loanDate, dueDate, fee);

        book.setAvailable(false);
        member.borrowBook(bookId);
        loanRepository.addActive(loan);

        Invoice inv = billingService.createInvoice(memberId, fee, "Book loan fee for bookId:" + bookId);
        notificationService.notifyMember(memberId, "Kitap ödünç alındı. Fatura: " + inv);

        return "Başarılı: Ödünç işlemi tamamlandı. Fatura ID: " + inv.getId();

    }

    //Continue Return book

}
