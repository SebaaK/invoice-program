package kots.invoiceprogram.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GoogleAuthTokenDto {

    @JsonProperty("tokenId")
    private String tokenId;
}
