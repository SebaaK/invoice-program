package kots.invoiceprogram.model.dto;

import kots.invoiceprogram.model.selectors.GTUType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceGtuDto {

    private Long id;
    private GTUType gtuType;
}
