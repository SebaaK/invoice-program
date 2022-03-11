package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.InvoicePayment;
import kots.invoiceprogram.repository.InvoicePaymentRepository;
import kots.invoiceprogram.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoicePaymentService {

    private final InvoicePaymentRepository repository;
    private final InvoiceService invoiceService;

    public List<InvoicePayment> getAllPayments(Long idBusiness, Long idInvoice) {
        return repository.findAllByInvoice(invoiceService.getSingleInvoice(idBusiness, idInvoice));
    }

    @Transactional
    public InvoicePayment addNewPayment(
            Long idBusiness,
            Long idInvoice,
            InvoicePayment createdInvoicePaymentDto) {
        Invoice invoice = invoiceService.getSingleInvoice(idBusiness, idInvoice);
        createdInvoicePaymentDto.setInvoice(invoice);
        InvoicePayment saveEntity = repository.save(createdInvoicePaymentDto);
        invoice.calcGrossPrice();
        return saveEntity;
    }

    @Transactional
    public void deletePaymentById(Long idBusiness, Long idInvoice, Long idPayment) {
        Invoice invoice = invoiceService.getSingleInvoice(idBusiness, idInvoice);
        repository.deleteByInvoiceAndAndId(invoice, idPayment);
        invoice.calcGrossPrice();
    }
}
