package kots.invoiceprogram.repository;

import kots.invoiceprogram.model.InvoiceGtu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceGtuRepository extends JpaRepository<InvoiceGtu, Long> {

}
