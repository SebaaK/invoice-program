package kots.invoiceprogram.controller;

import kots.invoiceprogram.model.dto.BusinessDto;
import kots.invoiceprogram.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/business")
class BusinessController {

    private final BusinessService businessService;

//    @GetMapping
//    public List<BusinessDto> getAllBusinessList() {
//
//    }
}
