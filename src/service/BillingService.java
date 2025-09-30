package service;

import java.util.ArrayList;
import java.util.List;

import model.Invoice;
import util.IdGenerator;

public class BillingService {

    private final List<Invoice> invoices = new ArrayList<>();

    public Invoice createInvoice(int memberId, double amount, String description) {

        Invoice invoice = new Invoice(IdGenerator.nextId(), memberId, amount, null, description);
        invoices.add(invoice);
        return invoice;
    }

    public List<Invoice> getAllInvoices(){
        return invoices;
    }

}
