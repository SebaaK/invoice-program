package kots.invoiceprogram.model.dto;

import kots.invoiceprogram.model.selectors.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    private Long id;
    private PaymentMethod paymentMethod;
    private String invoiceNumber;
    private Date createdDate;
    private Date issueDate;
    private Date dueDate;
    private BigDecimal grossPrice;
    private String otherCurrencyName;
    private BigDecimal otherCurrencyGrossPrice;
    private BigDecimal exchangeRate;
}
