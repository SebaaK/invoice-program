package kots.invoiceprogram.repository;

import kots.invoiceprogram.model.dto.InvoiceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceGtuRepository extends JpaRepository<InvoiceDto, Long> {

}
