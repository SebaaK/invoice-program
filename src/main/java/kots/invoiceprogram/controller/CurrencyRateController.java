package kots.invoiceprogram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kots.invoiceprogram.model.dto.CurrencyRateDto;
import kots.invoiceprogram.service.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
class CurrencyRateController {

    private final CurrencyRateService service;

    @GetMapping("/{idBusiness}/rate/{currency}")
    ResponseEntity<CurrencyRateDto> getCurrencyRate(
            @PathVariable
                    Long idBusiness,
            @PathVariable
                    String currency,
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate date) throws JsonProcessingException {

        if(currency.length() != 3)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Currency length must be 3 chars");

        return ResponseEntity.ok(service.getCurrencyRate(idBusiness, currency, date));
    }
}
