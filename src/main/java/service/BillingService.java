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

    public List<Invoice> getInvoicesByMemberId(int memberId){
        List<Invoice> result = new ArrayList<>();
        for(Invoice inv : invoices)
            if(inv.getMemberId() == memberId)
                result.add(inv);
        return result;
    }

    public Invoice getInvoiceById(int id){
        for(Invoice inv : invoices)
            if(inv.getId() == id)
                return inv;
        return null;
    }

}
