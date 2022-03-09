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
        Map<String, Double> filteredMap = new HashMap<>();
        for(Map.Entry<String, Double> entry : mapToFilter.entrySet()) {
            if(currency.equals(entry.getKey())) {
                filteredMap.put(entry.getKey(), entry.getValue());
                return filteredMap;
            }
        }
        return filteredMap;
    }
}
