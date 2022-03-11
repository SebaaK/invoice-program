package kots.invoiceprogram.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
public class InvoicePaymentDto {

    private Long id;
    private LocalDate paymentDate;
    private BigDecimal paymentValue;
}
