package model;

import java.util.HashSet;
import java.util.Set;

public class Member extends Person{
    private final Set<Integer> borrowedBookIds = new HashSet<>();
    public static final int BORROW_LIMIT = 5;

    public Member(int id, String name, String email, long personId) {
        super(id, name, email,  personId);
    }

    @Override
    public String getDisplayRole() {
        return "Member";
    }

    public Set<Integer> getBorrowedBookIds() {
        return borrowedBookIds;
    }

    public boolean canBorrow(){
        return borrowedBookIds.size() < BORROW_LIMIT;
    }

    public void borrowBook(int bookId){
        borrowedBookIds.add(bookId);
    }

    public void returnBook(int bookId){
        borrowedBookIds.remove(bookId);
    }

    @Override
    public String toString() {
        return String.format("Member{id=%d, name='%s', email='%s', borrowedBooks=%s, personId=%d}",
                getId(), getName(), getEmail(), borrowedBookIds, getPersonId());
    }




}
