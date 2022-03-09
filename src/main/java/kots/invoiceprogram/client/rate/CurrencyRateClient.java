package kots.invoiceprogram.client.rate;

import kots.invoiceprogram.model.dto.CurrencyRateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class CurrencyRateClient {

    private final static String URL = "https://api.vatcomply.com/rates";
    private final RestTemplate restTemplate;

    public CurrencyRateDto getRate(String currency, LocalDate historicalDate) {
        ResponseEntity<CurrencyRateDto> response = restTemplate.getForEntity(getUriTemplate(currency, historicalDate), CurrencyRateDto.class);

        if(response.getStatusCodeValue() != 200)
            throw new IllegalArgumentException("Not implemented yet!");

        return response.getBody();
    }

    private URI getUriTemplate(String currency, LocalDate historicalDate) {
        UriComponentsBuilder generatedURI = UriComponentsBuilder.fromUriString(URL)
                .queryParam("base", currency);

        if(historicalDate != null)
            generatedURI.queryParam("date", historicalDate);

        return generatedURI.build().toUri();
    }

}
