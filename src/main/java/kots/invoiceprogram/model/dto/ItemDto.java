package kots.invoiceprogram.model.dto;

import kots.invoiceprogram.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private Invoice invoice;
    private String name;
    private String ui;
    private Integer quantity;
    private BigDecimal netPrice;
    private Integer taxValue;
    private BigDecimal grossPrice;
    private Integer discount;
}
