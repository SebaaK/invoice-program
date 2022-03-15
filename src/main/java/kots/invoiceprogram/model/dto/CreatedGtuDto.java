package kots.invoiceprogram.model.dto;

import kots.invoiceprogram.model.selectors.GTUType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatedGtuDto {

    private GTUType gtuType;
}
