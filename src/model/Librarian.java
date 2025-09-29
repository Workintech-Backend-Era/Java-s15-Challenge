package model;

public class Librarian extends Person{

    public Librarian(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String getDisplayRole() {
        return "Librarian";
    }
    

}
