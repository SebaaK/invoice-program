package kots.invoiceprogram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoicePaymentDto {

    private Long id;
    private LocalDate paymentDate;
    private BigDecimal paymentValue;
}
