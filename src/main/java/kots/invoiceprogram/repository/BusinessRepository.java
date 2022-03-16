package kots.invoiceprogram.repository;

import kots.invoiceprogram.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Optional<Business> findByTaxId(Integer taxId);
}
