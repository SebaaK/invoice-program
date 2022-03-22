package kots.invoiceprogram;

import kots.invoiceprogram.model.*;
import kots.invoiceprogram.model.dto.CreatedItemDto;
import kots.invoiceprogram.model.selectors.GTUType;
import kots.invoiceprogram.model.selectors.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GenerateData {

    public static final int SIZE_INVOICE_LIST = 10;
    public static final PaymentMethod INVOICE_PAYMENT_METHOD = PaymentMethod.PRZELEW;
    public static final String INVOICE_CONST_INVOICE_NUMBER = "1/11/2022";
    public static final LocalDate INVOICE_CREATED_DATE = LocalDate.now();
    public static final LocalDate INVOICE_ISSUE_DATE = LocalDate.now();
    public static final LocalDate INVOICE_DUE_DATE = LocalDate.now();
    public static final BigDecimal INVOICE_NET_PRICE = BigDecimal.valueOf(100.00);
    public static final BigDecimal INVOICE_TAX_VALUE = BigDecimal.valueOf(23.00);
    public static final BigDecimal INVOICE_GROSS_PRICE = BigDecimal.valueOf(123.00);
    public static final BigDecimal INVOICE_INCLUDE_PAYMENT = BigDecimal.ZERO;
    public static final String INVOICE_CURRENCY_NAME = "PLN";
    public static final String INVOICE_OTHER_CURRENCY_NAME = "EUR";
    public static final BigDecimal INVOICE_OTHER_CURRENCY_GROSS_PRICE = BigDecimal.ONE;
    public static final BigDecimal INVOICE_EXCHANGE_RATE = new BigDecimal(3.39);

    public static final int SIZE_INVOICE_GTU_SET_LIST = 10;
    public static final int SIZE_ITEM_SET_LIST = 10;
    public static final int SIZE_INVOICE_PAYMENT_LIST = 10;
    public static final int SIZE_CREATED_ITEM_DTO_LIST = 10;

    public static Business getBusiness() {
        return Business.builder()
                .id(1L)
                .fullName("BusinessName")
                .address("BusinessAddress")
                .postalCode("00-000")
                .city("BusinessCity")
                .country("BusinessCountry")
                .taxId(1234567890)
                .emailAddress("businessMail@test.test")
                .build();
    }

    public static Customer getCustomer() {
        return Customer.builder()
                .id(1L)
                .customerName("CustomerName")
                .businessName("CustomerBusinessName")
                .businessAddress("CustomerAddress")
                .businessPostalCode("11-111")
                .businessCity("CustomerCity")
                .businessCountry("CustomerCountry")
                .taxId(1111111111)
                .emailAddress("CustomerMail@test.test")
                .build();
    }

    public static Invoice getInvoice() {
        return Invoice.builder()
                .id(1L)
                .paymentMethod(INVOICE_PAYMENT_METHOD)
                .invoiceNumber(INVOICE_CONST_INVOICE_NUMBER)
                .createdDate(INVOICE_CREATED_DATE)
                .issueDate(INVOICE_ISSUE_DATE)
                .dueDate(INVOICE_DUE_DATE)
                .netPrice(INVOICE_NET_PRICE)
                .taxValue(INVOICE_TAX_VALUE)
                .grossPrice(INVOICE_GROSS_PRICE)
                .includePayment(INVOICE_INCLUDE_PAYMENT)
                .currencyName(INVOICE_CURRENCY_NAME)
                .otherCurrencyName(INVOICE_OTHER_CURRENCY_NAME)
                .otherCurrencyGrossPrice(INVOICE_OTHER_CURRENCY_GROSS_PRICE)
                .exchangeRate(INVOICE_EXCHANGE_RATE)
                .build();
    }

    public static List<Invoice> getInvoiceList(Business business) {
        List<Invoice> invoiceList = new ArrayList<>();
        for(long i = 1; i <= SIZE_INVOICE_LIST; i++) {
            invoiceList.add(
                    Invoice.builder()
                            .id(i)
                            .business(business)
                            .paymentMethod(PaymentMethod.PRZELEW)
                            .invoiceNumber(i + "/11/2022")
                            .createdDate(LocalDate.now())
                            .issueDate(LocalDate.now())
                            .dueDate(LocalDate.now())
                            .netPrice(BigDecimal.ONE)
                            .taxValue(BigDecimal.ZERO)
                            .grossPrice(BigDecimal.ONE)
                            .includePayment(BigDecimal.ONE)
                            .currencyName("PLN")
                            .otherCurrencyName("EUR")
                            .otherCurrencyGrossPrice(BigDecimal.ONE)
                            .exchangeRate(new BigDecimal(3.39))
                            .build()
            );
        }
        return invoiceList;
    }

    public static Set<InvoiceGtu> getSetListInvoiceGtu() {
        Set<InvoiceGtu> invoiceGtuSet = new HashSet<>();
        for(long i = 1; i <= SIZE_INVOICE_GTU_SET_LIST; i++) {
            invoiceGtuSet.add(
                    InvoiceGtu.builder()
                            .id(i)
                            .gtuType(GTUType.GTU_01)
                            .build()
            );
        }
        return invoiceGtuSet;
    }

    public static Set<Item> getSetListItem() {
        Set<Item> itemSet = new HashSet<>();
        for(long i = 1; i <= SIZE_ITEM_SET_LIST; i++) {
            itemSet.add(
                    new Item(
                            i,
                            "ExampleItem",
                            (int) i,
                            new BigDecimal(100.00),
                            new BigDecimal(100.00),
                            0.23,
                            new BigDecimal(23.00),
                            new BigDecimal(123.00),
                            0
                    )
            );
        }
        return itemSet;
    }

    public static InvoicePayment getInvoicePayment() {
        return InvoicePayment.builder()
                .id(1L)
                .paymentDate(LocalDate.now())
                .paymentValue(BigDecimal.valueOf(100.00))
                .build();
    }

    public static List<InvoicePayment> getInvoicePaymentList() {
        List<InvoicePayment> invoicePaymentList = new ArrayList<>();
        for(long i = 1; i <= SIZE_INVOICE_PAYMENT_LIST; i++) {
            invoicePaymentList.add(
                    InvoicePayment.builder()
                            .id(i)
                            .paymentDate(LocalDate.now())
                            .paymentValue(BigDecimal.valueOf(100.00))
                            .build()
            );
        }
        return invoicePaymentList;
    }

    public static List<CreatedItemDto> getCreatedItemDtoList() {
        List<CreatedItemDto> createdItemDtoList = new ArrayList<>();
        for(int i = 1; i <= SIZE_CREATED_ITEM_DTO_LIST; i++) {
            new CreatedItemDto(
                      "ExampleName",
                    1,
                    BigDecimal.valueOf(100.00),
                    BigDecimal.valueOf(100.00),
                    0.23,
                    BigDecimal.valueOf(23.00),
                    BigDecimal.valueOf(123.00),
                    0
            );
        }
        return createdItemDtoList;
    }
}
