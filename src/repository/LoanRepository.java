package repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Loan;

public class LoanRepository {
    private final Map<Integer, Loan> activeLoans = new HashMap<>();
    private final List<Loan> history = new ArrayList<>();

    public void addActive(Loan loan){
        activeLoans.put(loan.getId(), loan);
    }

    public Loan getActiveLoanById(int id){
        return activeLoans.get(id);
    }

    public void removeActive(int id){
        Loan l = activeLoans.remove(id);
        if(l!=null){
            history.add(l);
        }
    }

    public Collection<Loan> getAllActive(){
        return activeLoans.values();
    }

    public List<Loan> getHistory(){
        return history;
    }

    public Loan findByBookId(int bookId){
        for(Loan l: activeLoans.values())
            if(l.getBookId() == bookId)
                return l;
        return null;
    }
}

//I will continue with service/BillingService.java
