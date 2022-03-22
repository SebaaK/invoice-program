package kots.invoiceprogram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private Integer quantity;
    private BigDecimal singleNetPrice;
    private BigDecimal netPrice;
    private Double taxPercent;
    private BigDecimal taxValue;
    private BigDecimal grossPrice;
    private Double discount;
}
