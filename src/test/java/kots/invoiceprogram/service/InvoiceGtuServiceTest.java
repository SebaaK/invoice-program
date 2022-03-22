package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.InvoiceGtu;
import kots.invoiceprogram.repository.InvoiceGtuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static kots.invoiceprogram.GenerateData.getInvoice;
import static kots.invoiceprogram.GenerateData.getSetListInvoiceGtu;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class InvoiceGtuServiceTest {

    @InjectMocks
    private InvoiceGtuService invoiceGtuService;

    @Mock
    private InvoiceGtuRepository repository;

    @Test
    void shouldUpdateGtuList() {
        // given
        doNothing().when(repository).deleteAllByInvoice(any(Invoice.class));

        // when
        Set<InvoiceGtu> resultList = invoiceGtuService.updateGtuList(getInvoice(), getSetListInvoiceGtu());

        // then
        assertThat(resultList).isNotEmpty();
        assertThat(resultList).hasSize(1);
        assertThat(resultList.iterator().next().getInvoice()).isNotNull();
    }
}