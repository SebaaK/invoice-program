package kots.invoiceprogram.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
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
