package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Customer;
import kots.invoiceprogram.model.dto.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerMapper {

    public Customer mapToCustomer(final CustomerDto customerDto) {
        return new Customer(
                customerDto.getCustomerName(),
                customerDto.getBusinessName(),
                customerDto.getBusinessAddress(),
                customerDto.getBusinessPostalCode(),
                customerDto.getBusinessCity(),
                customerDto.getBusinessCountry(),
                customerDto.getTaxId(),
                customerDto.getEmailAddress()
        );
    }

    public CustomerDto mapToCustomerDto(final Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getCustomerName(),
                customer.getBusinessName(),
                customer.getBusinessAddress(),
                customer.getBusinessPostalCode(),
                customer.getBusinessCity(),
                customer.getBusinessCountry(),
                customer.getTaxId(),
                customer.getEmailAddress()
        );
    }

    public List<CustomerDto> mapToCustomerDtoList(final List<Customer> customerList) {
        return customerList.stream()
                .map(this::mapToCustomerDto)
                .collect(Collectors.toList());
    }
}
