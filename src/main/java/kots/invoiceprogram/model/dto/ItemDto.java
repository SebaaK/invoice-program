package kots.invoiceprogram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ItemDto {

    private Long id;
    private String name;
    private String ui;
    private Integer quantity;
    private BigDecimal netPrice;
    private Double taxPercent;
    private BigDecimal taxValue;
    private BigDecimal grossPrice;
    private Double discount;
}
