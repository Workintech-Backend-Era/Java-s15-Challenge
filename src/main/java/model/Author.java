package model;

import java.util.Objects;


public class Author {
    private final int id;
    private String name;

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name, int id){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Author)) return false;
        Author other = (Author) o;
        return Objects.equals(name, other.name) && id == other.id;
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return name + " (id:" + id + ")";
    }
}
