package model;

public class Book {
    private final int id;
    private String title;
    private Author author;
    private Category category;
    private boolean available = true;
    private double price;

    public Book(int id, String title, Author author, Category category, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
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

    @Override
    public String toString(){
        return String.format("Book{id=%d, title='%s', author=%s, category=%s, available=%b, price=%.2f}"
        , id, title, author.getName(), category.getName(), available, price);
    }

    


}
