package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository repository;

    public List<Invoice> getAllInvoices(Long idBusiness) {
        return repository.searchAllByBusinessId(idBusiness).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Invoice getSingleInvoice(Long idInvoice, Long idBusiness) {
        return repository.searchByIdAndBusinessId(idInvoice, idBusiness).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
