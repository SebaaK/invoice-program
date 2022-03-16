package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.InvoicePayment;
import kots.invoiceprogram.model.dto.CreatedInvoicePaymentDto;
import kots.invoiceprogram.model.dto.InvoicePaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvoicePaymentMapperTest {

    @Autowired
    private InvoicePaymentMapper invoicePaymentMapper;

    @Test
    void shouldReturnInvoicePaymentDto() {
        // given
        LocalDate now = LocalDate.now().minusDays(1);
        InvoicePayment invoicePayment = new InvoicePayment(1L, null, now, new BigDecimal(100));

        // when
        InvoicePaymentDto result = invoicePaymentMapper.mapToInvoicePaymentDto(invoicePayment);

        // then
        assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals(now, result.getPaymentDate()),
                () -> assertEquals(new BigDecimal(100), result.getPaymentValue())

        );
    }

    @Test
    void shouldReturnInvoicePayment() {
        // given
        CreatedInvoicePaymentDto createdInvoicePaymentDto = new CreatedInvoicePaymentDto(new BigDecimal(100));

        // when
        InvoicePayment result = invoicePaymentMapper.mapToInvoicePayment(createdInvoicePaymentDto);

        // then
        assertAll(
                () -> assertEquals(null, result.getId()),
                () -> assertEquals(null, result.getInvoice()),
                () -> assertEquals(null, result.getPaymentDate()),
                () -> assertEquals(new BigDecimal(100), result.getPaymentValue())
        );
    }

    @Test
    void shouldReturnInvoicePaymentDtoList() {
        // given
        List<InvoicePayment> randomInvoicePaymentList = getRandomInvoicePaymentList();

        // when
        List<InvoicePaymentDto> result = invoicePaymentMapper.mapToInvoicePaymentDtoList(randomInvoicePaymentList);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(9);
    }

    private List<InvoicePayment> getRandomInvoicePaymentList() {
        List<InvoicePayment> invoicePaymentList = new ArrayList<>();
        for(long i = 1; i < 10; i++) {
            invoicePaymentList.add(new InvoicePayment(i, null, LocalDate.now(), new BigDecimal(100)));
        }
        return invoicePaymentList;
    }
}