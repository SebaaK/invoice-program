package kots.invoiceprogram.model.dto;

import kots.invoiceprogram.model.selectors.GTUType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceGtuDto {

    private Long id;
    private GTUType gtuType;
}
