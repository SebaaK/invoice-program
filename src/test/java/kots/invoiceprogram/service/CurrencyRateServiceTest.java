package kots.invoiceprogram.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kots.invoiceprogram.client.country.CountryInfo;
import kots.invoiceprogram.client.rate.CurrencyRateClient;
import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.CurrencyRateDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyRateServiceTest {

    public static final LocalDate DATE = LocalDate.now();
    public static final String BASE = "EUR";
    @InjectMocks
    private CurrencyRateService currencyRateService;

    @Mock
    private CurrencyRateClient currencyRateClient;

    @Mock
    private BusinessService businessService;

    @Mock
    private CountryInfo countryInfo;

    @Test
    void shouldGetCurrencyRate() throws JsonProcessingException {
        // given
        when(currencyRateClient.getRate("EUR", null)).thenReturn(getCurrencyRateDtoExampleData());
        when(businessService.getSingleBusiness(anyLong())).thenReturn(getBusiness());
        when(countryInfo.getCurrencyInfo(anyString())).thenReturn("PLN");

        // when
        CurrencyRateDto result = currencyRateService.getCurrencyRate(1L, "EUR", null);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getDate()).isEqualTo(DATE);
        assertThat(result.getBase()).isEqualTo("EUR");
        assertAll(
                () -> assertThat(result.getRates()).hasSize(1),
                () -> assertThat(result.getRates().get("PLN")).isEqualTo(4.7135)
        );
    }

    private Business getBusiness() {
        return Business.builder()
                .id(1L)
                .country("POLAND")
                .build();
    }

    private CurrencyRateDto getCurrencyRateDtoExampleData() {
        CurrencyRateDto currencyRateDto = new CurrencyRateDto();
        currencyRateDto.setDate(DATE);
        currencyRateDto.setBase(BASE);
        currencyRateDto.setRates(getMapListToCurrencyRateDto());
        return currencyRateDto;
    }

    private Map<String, Double> getMapListToCurrencyRateDto() {
        Map<String, Double> stringDoubleMap = new HashMap<>();
        stringDoubleMap.put("PLN", 4.7135);
        stringDoubleMap.put("USD", 1.1008);
        return stringDoubleMap;
    }

}