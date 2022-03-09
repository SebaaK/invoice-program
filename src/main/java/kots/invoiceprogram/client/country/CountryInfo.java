package kots.invoiceprogram.client.country;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CountryInfo {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public String getCurrencyInfo(String country) throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.readTree(restTemplate.getForObject(getUriTemplate(country), String.class));
        Map.Entry<String, JsonNode> stringIterator = jsonNode.get(0)
                .fields().next().getValue()
                .fields().next();
        return stringIterator.getKey();
    }

    private URI getUriTemplate(String country) {
        return UriComponentsBuilder.fromHttpUrl("https://restcountries.com/v3.1/name/" + country)
                .queryParam("fields", "currencies")
                .build()
                .encode()
                .toUri();
    }
}
