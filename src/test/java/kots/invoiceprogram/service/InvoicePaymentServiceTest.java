package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.InvoicePayment;
import kots.invoiceprogram.repository.InvoicePaymentRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class InvoicePaymentServiceTest {

    @InjectMocks
    private InvoicePaymentService invoicePaymentService;

    @Mock
    private InvoicePaymentRepository repository;

    @Mock
    private InvoiceService invoiceService;

    @Test
    void shouldCatchListOfInvoicePayment() {
        // given
        when(invoiceService.getSingleInvoice(anyLong(), anyLong())).thenReturn(getInvoice());
        when(repository.findAllByInvoice(any(Invoice.class))).thenReturn(getInvoicePaymentList());

        // when
        List<InvoicePayment> resultList = invoicePaymentService.getAllPayments(anyLong(), anyLong());

        // then
        assertThat(resultList).isNotEmpty();
        assertThat(resultList).hasSize(SIZE_INVOICE_PAYMENT_LIST);
    }

    @Test
    void shouldAddNewPayment() {
        // given
        LocalDate paymentDate = LocalDate.now().minusDays(1);
        InvoicePayment invoicePayment = InvoicePayment.builder()
                .paymentDate(paymentDate)
                .paymentValue(BigDecimal.ONE)
                .build();

        Invoice invoice = getInvoice();
        invoice.setItemList(getSetListItem());

        when(invoiceService.getSingleInvoice(anyLong(), anyLong())).thenReturn(invoice);
        when(repository.save(any(InvoicePayment.class))).thenReturn(invoicePayment);

        // when
        InvoicePayment result = invoicePaymentService.addNewPayment(1L, 1L, invoicePayment);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getInvoice()).isNotNull();
        assertThat(invoice.getIncludePayment()).isEqualTo(BigDecimal.ZERO);
    }

    @Test
    void shouldDeletePayment() {
        // given
        Invoice invoice = getInvoice();
        invoice.setItemList(getSetListItem());
        when(invoiceService.getSingleInvoice(anyLong(), anyLong())).thenReturn(invoice);

        // when
        invoicePaymentService.deletePaymentById(1L, 1L, 1L);

        // then
        verify(repository, times(1)).deleteByInvoiceAndAndId(any(Invoice.class), anyLong());
    }
}