package kots.invoiceprogram.model.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreatedItemDto {

    private String name;
    private int quantity;
    private BigDecimal netPrice;
    private double taxPercent;
    private BigDecimal taxValue;
    private BigDecimal grossPrice;
    private double discount;
}
