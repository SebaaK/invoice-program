package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.InvoiceGtu;
import kots.invoiceprogram.repository.InvoiceGtuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class InvoiceGtuService {

    private final InvoiceGtuRepository repository;

    //TODO: Poprawić na lepszy update
    public Set<InvoiceGtu> updateGtuList(Invoice invoice, Set<InvoiceGtu> gtuType) {
        repository.deleteAllByInvoice(invoice);

        for(InvoiceGtu gtu : gtuType) {
            gtu.setInvoice(invoice);
        }

        return gtuType;
    }
}
