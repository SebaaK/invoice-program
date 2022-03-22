package kots.invoiceprogram.model.dto;

import kots.invoiceprogram.model.selectors.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CreatedInvoiceDto {

    private List<CreatedItemDto> itemList;
    private List<CreatedGtuDto> gtuType;
    private PaymentMethod paymentMethod;
    private String invoiceNumber;
    private LocalDate createdDate;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private String currencyName;
    private String otherCurrencyName;
    private BigDecimal otherCurrencyGrossPrice;
    private BigDecimal exchangeRate;
}
