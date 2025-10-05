package model;

import java.time.LocalDate;

public class Loan {
    private final int id;
    private final int bookId;
    private final int memberId;
    private final LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned = false;
    private double feeCharged = 0.0;

    public Loan(int id, int bookId, int memberId, LocalDate loanDate, LocalDate dueDate, double feeCharged) {
        this.id = id;
        this.bookId = bookId;
        this.memberId = memberId;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.feeCharged = feeCharged;
    }

    public int getId() {
        return id;
    }

    public int getBookId() {
        return bookId;
    }

    public int getMemberId() {
        return memberId;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public double getFeeCharged() {
        return feeCharged;
    }

    public void setFeeCharged(double feeCharged) {
        this.feeCharged = feeCharged;
    }

    @Override
    public String toString() {
        return String.format("Loan{id=%d, bookId=%d, memberId=%d, loanDate=%s, dueDate=%s, returned=%s, fee=%.2f}",
                id, bookId, memberId, loanDate, dueDate, returned, feeCharged);
    }

}
