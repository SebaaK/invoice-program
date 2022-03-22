package kots.invoiceprogram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatedItemDto {

    //TODO: Poprawić na dobre pola. Dane które są wpisywane w formularzu
    private String name;
    private int quantity;
    private BigDecimal singleNetPrice;
    private BigDecimal netPrice;
    private double taxPercent;
    private BigDecimal taxValue;
    private BigDecimal grossPrice;
    private double discount;
}
