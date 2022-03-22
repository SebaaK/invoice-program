package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.selectors.PaymentMethod;
import kots.invoiceprogram.repository.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static kots.invoiceprogram.GenerateData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoiceServiceTest {

    @InjectMocks
    private InvoiceService invoiceService;

    @Mock
    private CustomerService customerService;

    @Mock
    private ItemService itemService;

    @Mock
    private InvoiceGtuService invoiceGtuService;

    @Mock
    private InvoiceRepository repository;

    @Mock
    private BusinessService businessService;

    @Test
    void shouldGetAllInvoiceLists() {
        // given
        when(businessService.getSingleBusiness(anyLong())).thenReturn(getBusiness());
        when(repository.findAllByBusiness(any(Business.class))).thenReturn(getInvoiceList(getBusiness()));

        // when
        List<Invoice> result = invoiceService.getAllInvoices(1L);

        // then
        assertThat(result).hasSize(SIZE_INVOICE_LIST);
    }

    @Test
    void shouldGetSingleInvoice() {
        // given
        Invoice invoice = getInvoice();
        invoice.setBusiness(getBusiness());
        when(repository.searchByIdAndBusinessId(anyLong(), anyLong())).thenReturn(Optional.of(invoice));

        // when & then
        assertDoesNotThrow(() -> invoiceService.getSingleInvoice(1L, 1L));
        Invoice result = invoiceService.getSingleInvoice(1L, 1L);
        assertThat(result).isNotNull();
        assertAll(
                () -> assertThat(result.getId()).isEqualTo(1),
                () -> assertThat(result.getPaymentMethod()).isEqualTo(INVOICE_PAYMENT_METHOD),
                () -> assertThat(result.getInvoiceNumber()).isEqualTo(INVOICE_CONST_INVOICE_NUMBER),
                () -> assertThat(result.getCreatedDate()).isEqualTo(INVOICE_CREATED_DATE),
                () -> assertThat(result.getIssueDate()).isEqualTo(INVOICE_ISSUE_DATE),
                () -> assertThat(result.getDueDate()).isEqualTo(INVOICE_DUE_DATE),
                () -> assertThat(result.getNetPrice()).isEqualTo(INVOICE_NET_PRICE),
                () -> assertThat(result.getTaxValue()).isEqualTo(INVOICE_TAX_VALUE),
                () -> assertThat(result.getGrossPrice()).isEqualTo(INVOICE_GROSS_PRICE),
                () -> assertThat(result.getIncludePayment()).isEqualTo(INVOICE_INCLUDE_PAYMENT),
                () -> assertThat(result.getCurrencyName()).isEqualTo(INVOICE_CURRENCY_NAME),
                () -> assertThat(result.getOtherCurrencyName()).isEqualTo(INVOICE_OTHER_CURRENCY_NAME),
                () -> assertThat(result.getOtherCurrencyGrossPrice()).isEqualTo(INVOICE_OTHER_CURRENCY_GROSS_PRICE),
                () -> assertThat(result.getExchangeRate()).isEqualTo(INVOICE_EXCHANGE_RATE)
        );
    }

    @Test
    void shouldThrowExceptionWhenGetSingleInvoice() {
        // given
        when(repository.searchByIdAndBusinessId(anyLong(), anyLong())).thenReturn(Optional.empty());

        // when & then
        assertThrows(
                ResponseStatusException.class,
                () -> invoiceService.getSingleInvoice(1L, 1L));
    }

    @Test
    void shouldCreateNewInvoice() {
        // given
        Invoice invoice = getInvoice();
        invoice.setGtuType(getSetListInvoiceGtu());
        invoice.setItemList(getSetListItem());

        when(businessService.getSingleBusiness(anyLong())).thenReturn(getBusiness());
        when(customerService.getCustomer(any(Business.class), anyLong())).thenReturn(getCustomer());
        when(repository.save(any(Invoice.class))).thenReturn(invoice);

        // when
        Invoice result = invoiceService.createNewInvoiceForCustomer(1L, 1L, invoice);

        // then
        assertThat(result).isNotNull();
    }

    @Test
    void shouldUpdateExistInvoice() {
        // given
        LocalDate date = LocalDate.now().minusDays(1);
        PaymentMethod paymentMethod = PaymentMethod.KARTA_KREDYTOWA;
        String invoiceNumber = "22/12/2222";
        BigDecimal grossPrice = BigDecimal.valueOf(248.00);
        String currencyName = "PLN";
        Invoice invoice = Invoice.builder()
                .paymentMethod(paymentMethod)
                .invoiceNumber(invoiceNumber)
                .createdDate(date)
                .issueDate(date)
                .dueDate(date)
                .grossPrice(grossPrice)
                .netPrice(BigDecimal.valueOf(200.00))
                .taxValue(BigDecimal.valueOf(48.00))
                .includePayment(BigDecimal.valueOf(248.00))
                .currencyName(currencyName)
                .build();
        when(repository.searchByIdAndBusinessId(anyLong(), anyLong())).thenReturn(Optional.of(getInvoice()));
        when(itemService.updateItemList(any(Invoice.class), any())).thenReturn(getSetListItem());
        when(invoiceGtuService.updateGtuList(any(Invoice.class), any())).thenReturn(getSetListInvoiceGtu());

        // when
        Invoice result = invoiceService.updateInvoice(1L, 1L, invoice);

        // then
        assertThat(result).isNotNull();
        assertAll(
                () -> assertThat(result.getItemList()).isNotNull(),
                () -> assertThat(result.getGtuType()).isNotNull(),
                () -> assertThat(result.getPaymentMethod()).isEqualTo(paymentMethod),
                () -> assertThat(result.getInvoiceNumber()).isEqualTo(invoiceNumber),
                () -> assertThat(result.getIssueDate()).isEqualTo(date),
                () -> assertThat(result.getDueDate()).isEqualTo(date),
                () -> assertThat(result.getGrossPrice()).isEqualTo(grossPrice),
                () -> assertThat(result.getCurrencyName()).isEqualTo(currencyName),
                () -> assertThat(result.getOtherCurrencyName()).isNull(),
                () -> assertThat(result.getOtherCurrencyGrossPrice()).isNull(),
                () -> assertThat(result.getExchangeRate()).isNull()
        );
    }

    @Test
    void shouldDeleteInvoice() {
        // given
        when(repository.searchByIdAndBusinessId(anyLong(), anyLong())).thenReturn(Optional.of(getInvoice()));

        // when
        invoiceService.deleteInvoice(1L, 1L);

        // then
        verify(repository, times(1)).delete(any(Invoice.class));
    }
}