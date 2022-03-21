package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.Customer;
import kots.invoiceprogram.model.dto.CustomerDto;
import kots.invoiceprogram.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    public static final String FULL_NAME = "Example name";
    public static final long ID = 1L;
    public static final String ADDRESS = "Kielecka 2";
    public static final String POSTAL_CODE = "88-888";
    public static final String CITY = "Kielce";
    public static final String POLAND = "POLAND";
    public static final int TAX_ID = 1234567890;
    public static final String EMAIL_ADDRESS = "example@test.test";

    public static final long CUSTOMER_ID = 1L;
    public static final String CUSTOMER_NAME = "CustomerName";
    public static final String CUSTOMER_BUSINESS_NAME = "BusinessName";
    public static final String CUSTOMER_ADDRESS = "Kielecka 5/2";
    public static final String CUSTOMER_POSTAL_CODE = "12-345";
    public static final String CUSTOMER_CITY = "Warszawa";
    public static final String CUSTOMER_COUNTRY = "POLAND";
    public static final int CUSTOMER_TAX_ID = 1123456789;
    public static final String CUSTOMER_EMAIL = "customer@test.test";

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository repository;

    private Customer customer;
    private Business business;

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .id(CUSTOMER_ID)
                .customerName(CUSTOMER_NAME)
                .businessName(CUSTOMER_BUSINESS_NAME)
                .businessAddress(CUSTOMER_ADDRESS)
                .businessPostalCode(CUSTOMER_POSTAL_CODE)
                .businessCity(CUSTOMER_CITY)
                .businessCountry(CUSTOMER_COUNTRY)
                .taxId(CUSTOMER_TAX_ID)
                .emailAddress(CUSTOMER_EMAIL)
                .build();

        business = Business.builder()
                .id(ID)
                .fullName(FULL_NAME)
                .address(ADDRESS)
                .postalCode(POSTAL_CODE)
                .city(CITY)
                .country(POLAND)
                .taxId(TAX_ID)
                .emailAddress(EMAIL_ADDRESS)
                .build();
    }

    @Test
    void shouldCreateNewCustomer() {
        // given
        when(repository.save(any(Customer.class))).thenReturn(customer);

        // when
        Customer result = customerService.createNewCustomer(customer, business);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getBusiness()).isNotNull();
    }

    @Test
    void shouldGetSingleCustomer() {
        // given
        when(repository.searchCustomerByIdAndBusiness(anyLong(), any(Business.class))).thenReturn(Optional.of(customer));

        // when & then
        assertDoesNotThrow(() -> customerService.getCustomer(business, 1L));
        Customer result = customerService.getCustomer(business, 1L);

        assertThat(result).isNotNull();
        assertAll(
                () -> assertThat(result.getId()).isEqualTo(CUSTOMER_ID),
                () -> assertThat(result.getCustomerName()).isEqualTo(CUSTOMER_NAME),
                () -> assertThat(result.getBusinessName()).isEqualTo(CUSTOMER_BUSINESS_NAME),
                () -> assertThat(result.getBusinessAddress()).isEqualTo(CUSTOMER_ADDRESS),
                () -> assertThat(result.getBusinessPostalCode()).isEqualTo(CUSTOMER_POSTAL_CODE),
                () -> assertThat(result.getBusinessCity()).isEqualTo(CUSTOMER_CITY),
                () -> assertThat(result.getBusinessCountry()).isEqualTo(CUSTOMER_COUNTRY)
        );
    }

    @Test
    void shouldThrowExceptionWhenGetCustomer() {
        // given
        when(repository.searchCustomerByIdAndBusiness(anyLong(), any(Business.class))).thenReturn(Optional.empty());

        // when & then
        assertThrows(
                ResponseStatusException.class,
                () -> customerService.getCustomer(business, 1L),
                "Customer not found"
        );
    }

    @Test
    void shouldCatchCustomerList() {
        // given
        List<Customer> customerList = new ArrayList<>();
        for(long i = 1; i < 10; i++) {
            customer.setId(i);
            customer.setBusiness(business);
            customerList.add(customer);
        }
        when(repository.searchCustomersByBusiness(business)).thenReturn(customerList);

        // when
        List<Customer> resultList = customerService.getCustomersList(business);

        // then
        assertThat(resultList).isNotNull();
        assertThat(resultList).hasSize(9);
    }

    @Test
    void shouldUpdateCustomer() {
        // given
        CustomerDto customerDto = new CustomerDto(
                null,
                "ChangeName",
                "ChangeBusinessName",
                "ChangeAddress",
                "00-000",
                "ChangeCity",
                "ChangeCountry",
                0000000000,
                "changeMail@test.test"
        );
        when(repository.searchCustomerByIdAndBusiness(anyLong(), any(Business.class))).thenReturn(Optional.of(customer));

        // when
        Customer result = customerService.updateCustomer(business, customerDto, 1L);

        // then
        assertThat(result).isNotNull();
        assertAll(
                () -> assertThat(result.getCustomerName()).isEqualTo("ChangeName"),
                () -> assertThat(result.getBusinessName()).isEqualTo("ChangeBusinessName"),
                () -> assertThat(result.getBusinessAddress()).isEqualTo("ChangeAddress"),
                () -> assertThat(result.getBusinessPostalCode()).isEqualTo("00-000"),
                () -> assertThat(result.getBusinessCity()).isEqualTo("ChangeCity"),
                () -> assertThat(result.getBusinessCountry()).isEqualTo("ChangeCountry"),
                () -> assertThat(result.getTaxId()).isEqualTo(0000000000),
                () -> assertThat(result.getEmailAddress()).isEqualTo("changeMail@test.test")
        );
    }

    @Test
    void shouldDeleteCustomer() {
        // given
        // when
        customerService.deleteCustomer(business, 1L);

        // then
        verify(repository, times(1)).deleteCustomerByIdAndBusiness(1L, business);
    }
}