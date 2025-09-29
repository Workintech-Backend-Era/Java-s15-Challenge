package repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Book;

public class BookRepository {
    private final Map<Integer, Book> books = new HashMap<>();

    public void addBook(Book book) {
        books.put(book.getId(), book);
    }

    public Book getById(int id) {
        return books.get(id);
    }

    public void remove(int id) {
        books.remove(id);
    }

    public Collection<Book> getAll() {
        return books.values();
    }

    public List<Book> findByTitle(String q) {
        List<Book> res = new ArrayList<>();
        for (Book b : books.values())
            if (b.getTitle().toLowerCase().contains(q.toLowerCase()))
                res.add(b);
        return res;
    }

    public List<Book> findByAuthor(String authorName) {
        List<Book> res = new ArrayList<>();
        for (Book b : books.values())
            if (b.getAuthor().getName().toLowerCase().contains(authorName.toLowerCase()))
                res.add(b);
        return res;
    }

    public List<Book> findByCategory(String categoryName) {
        List<Book> res = new ArrayList<>();
        for (Book b : books.values())
            if (b.getCategory().getName().toLowerCase().contains(categoryName.toLowerCase()))
                res.add(b);
        return res;
    }

}
