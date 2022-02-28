package kots.invoiceprogram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessDto {

    private Long id;
    private String fullName;
    private String address;
    private String postalCode;
    private String city;
    private Integer taxId;
    private String emailAddress;
}
