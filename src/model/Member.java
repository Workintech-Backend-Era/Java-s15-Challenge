package model;

import java.util.HashSet;
import java.util.Set;

public class Member extends Person{
    private final Set<Integer> borrowedBookIds = new HashSet<>();
    public static final int BORROW_LIMIT = 5;

    public Member(int id, String name, String email) {
        super(id, name, email);
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



}
