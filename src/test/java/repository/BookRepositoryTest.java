package repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import model.Category;
import model.Author;
import model.Book;
import util.IdGenerator;
import repository.BookRepository;


public class BookRepositoryTest {

    private BookRepository bookRepo;

    @BeforeEach
    public void setUp() {
        bookRepo = new BookRepository();
    }

    @Test
    public void testAddAndFindBook(){
        Author a1 = new Author(1, "Yalçın Küçük");
        Category c1 = new Category(1, "Araştırma");
        Book book = new Book(IdGenerator.nextId(),"The Great Gatsby", a1, c1, 10.99, "9780743273565");
        bookRepo.addBook(book);
        Book fetched = bookRepo.getById(book.getId());
        assertNotNull(fetched);
        assertEquals(book, fetched);
    }

    @Test
    public void testRemoveBook(){
        Author a1 = new Author(1, "Yalçın Küçük");
        Category c1 = new Category(1, "Araştırma");
        Book book = new Book(IdGenerator.nextId(),"The Great Gatsby", a1, c1, 10.99, "9780743273565");
        bookRepo.addBook(book);
        bookRepo.remove(book.getId());
        assertNull(bookRepo.getById(book.getId()));
    }

}
