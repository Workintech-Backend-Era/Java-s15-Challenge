package model;

import java.util.Objects;

public class Category {

    private final int id;
    private String name;

    public Category(int id, String name) {
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
        if(!(o instanceof Category)) return false;
        Category other = (Category) o;
        return Objects.equals(name,other.name);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name + " (id:" + id + ")";
    }

}
