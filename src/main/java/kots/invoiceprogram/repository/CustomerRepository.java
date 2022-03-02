package kots.invoiceprogram.repository;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> searchCustomerByIdAndBusiness(Long id, Business business);
    List<Customer> searchCustomersByBusiness(Business business);
    void deleteCustomerByIdAndBusiness(Long id, Business business);
}
