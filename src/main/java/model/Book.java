package model;

import java.util.Objects;

public class Book {
    private final int id;
    private String title;
    private Author author;
    private Category category;
    private boolean available = true;
    private double price;
    private String isbn;

    public Book(int id, String title, Author author, Category category, double price, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }


    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Book)) return false;
        Book other = (Book) o;
        return Objects.equals(isbn, other.isbn);
    }

    @Override
    public int hashCode(){
        return Objects.hash(isbn);
    }

    @Override
    public String toString(){
        return String.format("Book{id=%d, title='%s', author=%s, category=%s, available=%b, price=%.2f}"
        , id, title, author.getName(), category.getName(), available, price);
    }




}
