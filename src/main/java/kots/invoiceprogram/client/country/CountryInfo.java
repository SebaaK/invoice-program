package kots.invoiceprogram.client.country;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CountryInfo {

    private final RestTemplate restTemplate;

    public String getCurrencyInfo(String country) {
        return restTemplate.getForObject(getUriTemplate(country), String.class);
    }

    private URI getUriTemplate(String country) {
        return UriComponentsBuilder.fromHttpUrl("https://restcountries.com/v3.1/name/" + country)
                .queryParam("fields", "currencies")
                .build()
                .encode()
                .toUri();
    }
}
