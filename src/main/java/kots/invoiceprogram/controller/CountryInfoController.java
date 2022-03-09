package kots.invoiceprogram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kots.invoiceprogram.client.country.CountryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/country")
class CountryInfoController {

    private final CountryInfo countryInfo;

    @GetMapping("/{country}")
    ResponseEntity<String> getCurrencyInfo(@PathVariable String country) throws JsonProcessingException {
        return ResponseEntity.ok(countryInfo.getCurrencyInfo(country));
    }
}
