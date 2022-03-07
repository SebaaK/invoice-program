package kots.invoiceprogram.repository;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findAllByBusiness(Business business);

    @Query("select i from Invoice i " +
            "left join fetch i.business " +
            "left join fetch i.customer " +
            "left join fetch i.itemList " +
            "left join fetch i.gtuType " +
            "where i.id=:invoiceId and i.business.id=:businessId")
    Optional<Invoice> searchByIdAndBusinessId(@Param("invoiceId") Long invoiceId, @Param("businessId") Long businessId);
}
