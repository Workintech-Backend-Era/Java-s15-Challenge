package model;

import java.util.Objects;

public class Librarian extends Person{

    public Librarian(int id, String name, String email, long personId) {
        super(id, name, email, personId);
    }

    @Override
    public String getDisplayRole() {
        return "Librarian";
    }

    @Override
    public String toString() {
        return String.format("Librarian{id=%d, name='%s', email='%s', personId=%d}",
                getId(), getName(), getEmail(), getPersonId());
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Librarian)) return false;
        Librarian other = (Librarian) o;
        return Objects.equals(getName(), other.getName()) &&
               Objects.equals(getEmail(), other.getEmail());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getName(), getEmail());
    }

}
