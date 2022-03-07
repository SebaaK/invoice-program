package kots.invoiceprogram.repository;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    void removeAllByInvoice(Invoice invoice);
}
