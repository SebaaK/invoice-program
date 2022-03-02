package kots.invoiceprogram.repository;

import kots.invoiceprogram.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    Optional<List<Invoice>> searchAllByBusinessId(Long businessId);

    @Query("select i from Invoice i where i.id=:invoiceId and i.business.id=:businessId")
    Optional<Invoice> searchByIdAndBusinessId(@Param("invoiceId") Long invoiceId, @Param("businessId") Long businessId);
}
