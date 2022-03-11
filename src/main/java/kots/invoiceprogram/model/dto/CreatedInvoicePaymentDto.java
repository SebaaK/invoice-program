package kots.invoiceprogram.model.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreatedInvoicePaymentDto {

    private BigDecimal paymentValue;
}
