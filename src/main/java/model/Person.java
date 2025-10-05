package model;

public abstract class Person {
    private final int id;
    private String name;
    private String email;
    private long personId;

    public Person(int id, String name, String email, long personId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.personId = personId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract String getDisplayRole();

}
