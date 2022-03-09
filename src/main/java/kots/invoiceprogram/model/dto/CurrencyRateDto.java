package kots.invoiceprogram.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class CurrencyRateDto {

    private LocalDate date;
    private String base;
    private Map<String, Double> rates;
}
