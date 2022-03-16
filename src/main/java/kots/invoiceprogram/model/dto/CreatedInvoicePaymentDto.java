package kots.invoiceprogram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatedInvoicePaymentDto {

    private BigDecimal paymentValue;
}
