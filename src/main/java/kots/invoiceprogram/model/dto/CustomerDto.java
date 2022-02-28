package kots.invoiceprogram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Long id;
    private String customerName;
    private String businessName;
    private String businessAddress;
    private String businessPostalCode;
    private String businessCity;
    private String businessCountry;
    private Integer taxId;
    private String emailAddress;
}
