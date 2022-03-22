package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.dto.CreatedGtuDto;
import kots.invoiceprogram.model.dto.CreatedInvoiceDto;
import kots.invoiceprogram.model.dto.InvoiceDto;
import kots.invoiceprogram.model.selectors.GTUType;
import kots.invoiceprogram.model.selectors.PaymentMethod;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static kots.invoiceprogram.GenerateData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InvoiceMapperTest {

    @InjectMocks
    private InvoiceMapper invoiceMapper;

    @Mock
    private BusinessMapper businessMapper;

    @Mock
    private CustomerMapper customerMapper;

    @Mock
    private GtuTypeMapper gtuTypeMapper;

    @Mock
    private InvoicePaymentMapper invoicePaymentMapper;

    @Mock
    private ItemMapper itemMapper;

    @Test
    void shouldReturnInvoiceClass() {
        // given
        PaymentMethod paymentMethod = PaymentMethod.PRZELEW;
        String invoiceNumber = "11/11/2022";
        LocalDate date = LocalDate.now();
        String currencyName = "PLN";
        String otherCurrencyName = "EUR";
        BigDecimal otherCurrencyGrossPrice = BigDecimal.valueOf(123.00);
        BigDecimal exchangeRate = BigDecimal.TEN;
        CreatedInvoiceDto createdInvoiceDto = new CreatedInvoiceDto(
                getCreatedItemDtoList(),
                List.of(new CreatedGtuDto(GTUType.GTU_01)),
                paymentMethod,
                invoiceNumber,
                date,
                date,
                date,
                currencyName,
                otherCurrencyName,
                otherCurrencyGrossPrice,
                exchangeRate
        );
        when(itemMapper.mapToItemDtoList(anyList())).thenReturn(getSetListItem());
        when(gtuTypeMapper.mapToInvoiceGtuSetList(anyList())).thenReturn(getSetListInvoiceGtu());

        // when
        Invoice result = invoiceMapper.mapToInvoice(createdInvoiceDto);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getItemList()).isNotEmpty();
        assertThat(result.getGtuType()).isNotEmpty();
        assertThat(result.getPaymentMethod()).isEqualTo(paymentMethod);
        assertThat(result.getInvoiceNumber()).isEqualTo(invoiceNumber);
        assertThat(result.getCreatedDate()).isEqualTo(date);
        assertThat(result.getIssueDate()).isEqualTo(date);
        assertThat(result.getDueDate()).isEqualTo(date);
        assertThat(result.getGrossPrice()).isNull();
        assertThat(result.getCurrencyName()).isEqualTo(currencyName);
        assertThat(result.getOtherCurrencyName()).isEqualTo(otherCurrencyName);
        assertThat(result.getOtherCurrencyGrossPrice()).isEqualTo(otherCurrencyGrossPrice);
        assertThat(result.getExchangeRate()).isEqualTo(exchangeRate);
    }

    @Test
    void shouldReturnInvoiceDtoWithoutDetails() {
        // given
        // when
        InvoiceDto result = invoiceMapper.invoiceDtoWithoutDetails(getInvoice());

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getBusiness()).isNull();
        assertThat(result.getCustomer()).isNull();
        assertThat(result.getPaymentsList()).isNull();
        assertThat(result.getItemList()).isNull();
        assertThat(result.getGtuType()).isNull();
        assertThat(result.getPaymentMethod()).isEqualTo(INVOICE_PAYMENT_METHOD);
        assertThat(result.getInvoiceNumber()).isEqualTo(INVOICE_CONST_INVOICE_NUMBER);
        assertThat(result.getCreatedDate()).isNull();
        assertThat(result.getIssueDate()).isEqualTo(INVOICE_ISSUE_DATE);
        assertThat(result.getDueDate()).isEqualTo(INVOICE_DUE_DATE);
        assertThat(result.getNetPrice()).isEqualTo(INVOICE_NET_PRICE);
        assertThat(result.getTaxValue()).isEqualTo(INVOICE_TAX_VALUE);
        assertThat(result.getGrossPrice()).isEqualTo(INVOICE_GROSS_PRICE);
        assertThat(result.getIncludePayment()).isEqualTo(INVOICE_INCLUDE_PAYMENT);
        assertThat(result.getCurrencyName()).isEqualTo(INVOICE_CURRENCY_NAME);
        assertThat(result.getOtherCurrencyName()).isEqualTo(INVOICE_OTHER_CURRENCY_NAME);
        assertThat(result.getOtherCurrencyGrossPrice()).isEqualTo(INVOICE_OTHER_CURRENCY_GROSS_PRICE);
        assertThat(result.getExchangeRate()).isEqualTo(INVOICE_EXCHANGE_RATE);
    }

    @Test
    void shouldReturnInvoiceDtoListWithoutDetails() {
        // given
        // when
        List<InvoiceDto> resultList = invoiceMapper.invoiceDtoWithoutDetailsList(getInvoiceList(null));

        // then
        assertThat(resultList).isNotEmpty();
        assertThat(resultList).hasSize(SIZE_INVOICE_LIST);
    }

    @Test
    void shouldReturnInvoiceDtoWithFullDetails() {
        // given
        Invoice invoice = getInvoice();
        invoice.setBusiness(getBusiness());
        invoice.setCustomer(getCustomer());
        invoice.setInvoicePayment(getInvoicePaymentList());
        invoice.setItemList(getSetListItem());
        invoice.setGtuType(getSetListInvoiceGtu());
        // when
        InvoiceDto result = invoiceMapper.invoiceDtoWithoutDetails(invoice);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1);
        assertThat(result.getPaymentMethod()).isEqualTo(INVOICE_PAYMENT_METHOD);
        assertThat(result.getInvoiceNumber()).isEqualTo(INVOICE_CONST_INVOICE_NUMBER);
        assertThat(result.getIssueDate()).isEqualTo(INVOICE_ISSUE_DATE);
        assertThat(result.getDueDate()).isEqualTo(INVOICE_DUE_DATE);
        assertThat(result.getNetPrice()).isEqualTo(INVOICE_NET_PRICE);
        assertThat(result.getTaxValue()).isEqualTo(INVOICE_TAX_VALUE);
        assertThat(result.getGrossPrice()).isEqualTo(INVOICE_GROSS_PRICE);
        assertThat(result.getIncludePayment()).isEqualTo(INVOICE_INCLUDE_PAYMENT);
        assertThat(result.getCurrencyName()).isEqualTo(INVOICE_CURRENCY_NAME);
        assertThat(result.getOtherCurrencyName()).isEqualTo(INVOICE_OTHER_CURRENCY_NAME);
        assertThat(result.getOtherCurrencyGrossPrice()).isEqualTo(INVOICE_OTHER_CURRENCY_GROSS_PRICE);
        assertThat(result.getExchangeRate()).isEqualTo(INVOICE_EXCHANGE_RATE);
    }
}