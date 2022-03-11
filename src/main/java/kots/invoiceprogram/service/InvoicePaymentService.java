package kots.invoiceprogram.service;

import kots.invoiceprogram.model.InvoicePayment;
import kots.invoiceprogram.repository.InvoicePaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoicePaymentService {

    private final InvoicePaymentRepository repository;
    private final InvoiceService invoiceService;

    public List<InvoicePayment> getAllPayments(Long idBusiness, Long idInvoice) {
        return repository.findAllByInvoice(invoiceService.getSingleInvoice(idBusiness, idInvoice));
    }

    public InvoicePayment addNewPayment(
            Long idBusiness,
            Long idInvoice,
            InvoicePayment createdInvoicePaymentDto) {
        createdInvoicePaymentDto.setInvoice(invoiceService.getSingleInvoice(idBusiness, idInvoice));
        return repository.save(createdInvoicePaymentDto);
    }

    public void deletePaymentById(Long idBusiness, Long idInvoice, Long idPayment) {
        repository.deleteByInvoiceAndAndId(invoiceService.getSingleInvoice(idBusiness, idInvoice), idPayment);
    }
}
