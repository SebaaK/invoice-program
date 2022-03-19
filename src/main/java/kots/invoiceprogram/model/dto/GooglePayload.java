package kots.invoiceprogram.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class GooglePayload {

    private Long businessId;
    private String picture;
    private String name;
    private String email;
}
