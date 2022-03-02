package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.Customer;
import kots.invoiceprogram.model.dto.CustomerDto;
import kots.invoiceprogram.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;

    public Customer createNewCustomer(Customer customer, Business business) {
        customer.setBusiness(business);
        return repository.save(customer);
    }

    public Customer getCustomer(Business business, Long idCustomer) {
        return repository.searchCustomerByIdAndBusiness(idCustomer, business).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Customer> getCustomersList(Business business) {
        return repository.searchCustomersByBusiness(business);
    }

    @Transactional
    public Customer updateCustomer(Business business, CustomerDto customerDto, Long idCustomer) {
        Customer customer = getCustomer(business, idCustomer);
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setBusinessName(customerDto.getBusinessName());
        customer.setBusinessAddress(customerDto.getBusinessAddress());
        customer.setBusinessPostalCode(customerDto.getBusinessPostalCode());
        customer.setBusinessCity(customerDto.getBusinessCity());
        customer.setBusinessCountry(customerDto.getBusinessCountry());
        customer.setTaxId(customerDto.getTaxId());
        customer.setEmailAddress(customerDto.getEmailAddress());
        return customer;
    }

    public void deleteCustomer(Business business, Long idCustomer) {
        repository.deleteCustomerByIdAndBusiness(idCustomer, business);
    }
}
