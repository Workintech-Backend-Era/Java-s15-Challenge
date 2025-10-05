package repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import model.Librarian;


public class LibrarianRepository {

    private final Map<Integer, Librarian> librarians = new HashMap<>();

    public void add(Librarian librarian) {
        librarians.put(librarian.getId(), librarian);
    }

    public Librarian getById(int id) {
        return librarians.get(id);
    }

    public void remove(int id) {
        librarians.remove(id);
    }

    public Collection<Librarian> getAll() {
        return librarians.values();
    }

    public Librarian findByName(String name) {
        for (Librarian l : librarians.values())
            if (l.getName().equalsIgnoreCase(name))
                return l;
        return null;
    }



}
