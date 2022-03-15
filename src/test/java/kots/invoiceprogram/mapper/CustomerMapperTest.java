package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Customer;
import kots.invoiceprogram.model.dto.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    void shouldReturnCustomer() {
        // given
        CustomerDto customerDto = new CustomerDto(
                1L,
                "Jan Kowalski",
                "TEST Jan Kowalski",
                "Kielecka 5",
                "21-412",
                "Kielce",
                "POLAND",
                1234456789,
                "example@test.test"
        );

        // when
        Customer result = customerMapper.mapToCustomer(customerDto);

        // then
        assertAll(
                () -> assertEquals(null, result.getId()),
                () -> assertEquals("Jan Kowalski", result.getCustomerName()),
                () -> assertEquals("TEST Jan Kowalski", result.getBusinessName()),
                () -> assertEquals("Kielecka 5", result.getBusinessAddress()),
                () -> assertEquals("21-412", result.getBusinessPostalCode()),
                () -> assertEquals("Kielce", result.getBusinessCity()),
                () -> assertEquals("POLAND", result.getBusinessCountry()),
                () -> assertEquals(1234456789, result.getTaxId()),
                () -> assertEquals("example@test.test", result.getEmailAddress())
        );
    }

    @Test
    void shouldReturnCustomerDto() {
        // given
        Customer customer = new Customer(
                "Jan Kowalski",
                "TEST Jan Kowalski",
                "Kielecka 5",
                "21-412",
                "Kielce",
                "POLAND",
                1234456789,
                "example@test.test"
        );
        customer.setId(1L);

        // when
        CustomerDto result = customerMapper.mapToCustomerDto(customer);

        // then
        assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("Jan Kowalski", result.getCustomerName()),
                () -> assertEquals("TEST Jan Kowalski", result.getBusinessName()),
                () -> assertEquals("Kielecka 5", result.getBusinessAddress()),
                () -> assertEquals("21-412", result.getBusinessPostalCode()),
                () -> assertEquals("Kielce", result.getBusinessCity()),
                () -> assertEquals("POLAND", result.getBusinessCountry()),
                () -> assertEquals(1234456789, result.getTaxId()),
                () -> assertEquals("example@test.test", result.getEmailAddress())
        );
    }

    @Test
    void shouldReturnCustomerDtoList() {
        // given
        List<Customer> customerList = getCustomerList();

        // when
        List<CustomerDto> resultList = customerMapper.mapToCustomerDtoList(customerList);

        // then
        assertThat(resultList).isNotEmpty();
        assertThat(resultList).hasSize(9);
    }

    private List<Customer> getCustomerList() {
        List<Customer> customerList = new ArrayList<>();

        for(long i = 1; i < 10; i++) {
            Customer customer = new Customer(
                    "Jan Kowalski",
                    "TEST Jan Kowalski",
                    "Kielecka 5",
                    "21-412",
                    "Kielce",
                    "POLAND",
                    1234456789,
                    "example@test.test"
            );
            customer.setId(i);
            customerList.add(customer);
        }
        return customerList;
    }
}