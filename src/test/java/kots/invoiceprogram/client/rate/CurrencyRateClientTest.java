package kots.invoiceprogram.client.rate;

import kots.invoiceprogram.model.dto.CurrencyRateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyRateClientTest {

    @InjectMocks
    private CurrencyRateClient currencyRateClient;

    @Mock
    private RestTemplate restTemplate;

    private CurrencyRateDto currencyRateDto;

    @BeforeEach
    void setupData() {
        Map<String, Double> map = new HashMap<>();
        map.put("EUR", 4.75);
        currencyRateDto = new CurrencyRateDto();
        currencyRateDto.setBase("PLN");
        currencyRateDto.setRates(map);
    }

    @Test
    void shouldResponseCurrencyRate() throws URISyntaxException {
        // given
        LocalDate localDate = LocalDate.now().minusDays(1);
        URI uri = new URI("https://api.vatcomply.com/rates?base=PLN");
        currencyRateDto.setDate(localDate);
        when(restTemplate.getForEntity(uri, CurrencyRateDto.class)).thenReturn(ResponseEntity.ok(currencyRateDto));

        // when
        CurrencyRateDto result = currencyRateClient.getRate("PLN", null);

        // then
        assertEquals(localDate, result.getDate());
        assertEquals("PLN", result.getBase());
        assertEquals(1, result.getRates().size());
    }

    @Test
    void shouldResponseCurrencyRateWithDifferentDate() throws URISyntaxException {
        // given
        LocalDate localDate = LocalDate.now().minusDays(7);
        URI uri = new URI("https://api.vatcomply.com/rates?base=PLN&date=" + localDate);
        currencyRateDto.setDate(localDate);
        when(restTemplate.getForEntity(uri, CurrencyRateDto.class)).thenReturn(ResponseEntity.ok(currencyRateDto));

        // when
        CurrencyRateDto result = currencyRateClient.getRate("PLN", localDate);

        // then
        assertEquals(localDate, result.getDate());
        assertEquals("PLN", result.getBase());
        assertEquals(1, result.getRates().size());
    }

    @Test
    void shouldThrowError() throws URISyntaxException {
        // given
        LocalDate localDate = LocalDate.now().minusDays(1);
        URI uri = new URI("https://api.vatcomply.com/rates?base=PLNX");
        when(restTemplate.getForEntity(uri, CurrencyRateDto.class)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        // when
        // then
        assertThrows(
                IllegalArgumentException.class,
                () -> currencyRateClient.getRate("PLNX", null),
                "Not implemented yet!");
    }
}