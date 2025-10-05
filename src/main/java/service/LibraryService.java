package service;

import java.time.LocalDate;
import java.util.List;

import model.Author;
import model.Book;
import model.Category;
import model.Invoice;
import model.Librarian;
import model.Loan;
import model.Member;
import repository.BookRepository;
import repository.LibrarianRepository;
import repository.LoanRepository;
import repository.UserRepository;
import util.IdGenerator;


public class LibraryService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final LoanRepository loanRepository;
    private final LibrarianRepository librarianRepository;
    private final BillingService billingService;
    private final NotificationService notificationService;

    public LibraryService(BookRepository bookRepository, UserRepository userRepository, LoanRepository loanRepository,
            BillingService billingService, NotificationService notificationService, LibrarianRepository librarianRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
        this.billingService = billingService;
        this.notificationService = notificationService;
        this.librarianRepository = librarianRepository;
    }

    public Book addBook(String title, Author author, Category cat, double price, String isbn) {
        int id = IdGenerator.nextId();
        if(isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("ISBN boş olamaz.");
        }
        if(bookRepository.getAll().stream().anyMatch(b -> b.getIsbn().equals(isbn))) {
            throw new IllegalArgumentException("Bu ISBN ile zaten bir kitap mevcut: " + isbn);
        }
        Book book = new Book(id, title, author, cat, price, isbn);
        bookRepository.addBook(book);
        return book;
    }

    public boolean updateBook(int id, String newTitle, Author newAuthor, Category newCat, Double newPrice) {
        Book b = bookRepository.getById(id);
        if (b == null)
            return false;
        b.setTitle(newTitle);
        b.setAuthor(newAuthor);
        b.setCategory(newCat);
        b.setPrice(newPrice);
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

    public Member addMember(String name, String email, long personId) {
        int id = IdGenerator.nextId();
        Member member = new Member(id, name, email, personId);
        userRepository.add(member);
        return member;
    }

    public Librarian addLibrarian(String name, String email, long personId) {
        int id = IdGenerator.nextId();
        Librarian librarian = new Librarian(id, name, email, personId);
        if(librarianRepository.findByPersonId(personId) != null && librarianRepository.findByPersonId(personId).equals(librarian)) {
            throw new IllegalArgumentException("Bu personel ID ile zaten bir kütüphaneci mevcut: " + personId);
        }
        librarianRepository.add(librarian);
        return librarian;
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

    public String returnBook(int memberId, int bookId){
        Member m = userRepository.getById(memberId);
        Book b = bookRepository.getById(bookId);
        if(m==null || b==null) return "Üye veya kitap bulunamadı.";
        Loan loan = loanRepository.findByBookId(bookId);
        if(loan == null) return "Bu kitap için aktif ödünç kaydı bulunamadı.";
        if(loan.getMemberId() != memberId) return "Bu kitabı bu üye almamış.";

        loan.setReturned(true);
        loanRepository.removeActive(loan.getId());
        b.setAvailable(true);
        m.returnBook(bookId);

        Invoice refund = billingService.createInvoice(memberId, -loan.getFeeCharged(), "Refund for loanId:" + loan.getId());
        notificationService.notifyMember(memberId, "Kitap iade edildi. İade: " + refund);

        return "İade başarılı. İade faturası ID: " + refund.getId();
    }

}
