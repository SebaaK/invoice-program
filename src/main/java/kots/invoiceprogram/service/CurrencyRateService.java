package kots.invoiceprogram.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import kots.invoiceprogram.client.country.CountryInfo;
import kots.invoiceprogram.client.rate.CurrencyRateClient;
import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.CurrencyRateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    private final CurrencyRateClient currencyRateClient;
    private final BusinessService businessService;
    private final CountryInfo countryInfo;

    public CurrencyRateDto getCurrencyRate(Long idBusiness, String currency, LocalDate date) throws JsonProcessingException {
        CurrencyRateDto rate = currencyRateClient.getRate(currency, date);
        Business singleBusiness = businessService.getSingleBusiness(idBusiness);
        rate.setRates(filterByCurrency(countryInfo.getCurrencyInfo(singleBusiness.getCountry()), rate.getRates()));
        return rate;
    }

    private Map<String, Double> filterByCurrency(String currency, Map<String, Double> mapToFilter) {
        return mapToFilter.entrySet().stream()
                .filter(item -> currency.equals(item.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
