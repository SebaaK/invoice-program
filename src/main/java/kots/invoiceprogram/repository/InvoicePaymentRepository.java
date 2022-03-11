package kots.invoiceprogram.repository;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.InvoicePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface InvoicePaymentRepository extends JpaRepository<InvoicePayment, Long> {

    List<InvoicePayment> findAllByInvoice(Invoice invoice);
    void deleteByInvoiceAndAndId(Invoice invoice, Long id);
}
