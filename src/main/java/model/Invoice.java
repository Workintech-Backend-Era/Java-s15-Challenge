package model;

import java.time.LocalDate;

public class Invoice {
    private final int id;
    private final int memberId;
    private final double amount;
    private final LocalDate date;
    private final String description;

    public Invoice(int id, int memberId, double amount, LocalDate date, String description) {
        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getMemberId() {
        return memberId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return String.format("Invoice{id=%d, memberId=%d, amount=%.2f, date=%s, desc='%s'}",
                id, memberId, amount, date, description);
    }

}
