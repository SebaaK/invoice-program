package kots.invoiceprogram.controller;

import kots.invoiceprogram.mapper.BusinessMapper;
import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.dto.BusinessDto;
import kots.invoiceprogram.service.BusinessService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
@CrossOrigin("*")
class BusinessController {

    private final BusinessService service;
    private final BusinessMapper mapper;

    @GetMapping
    ResponseEntity<List<BusinessDto>> getBusinessList() {
        return ResponseEntity.ok(mapper.mapToBusinessDtoList(service.getBusinessList()));
    }

    @GetMapping("/{id}")
    ResponseEntity<BusinessDto> getOneBusiness(@PathVariable Long id) {
        return ResponseEntity.ok(mapper.mapToBusinessDto(service.getSingleBusiness(id)));
    }

    @GetMapping("/taxNumber/{taxId}")
    ResponseEntity<BusinessDto> getBusinessByTaxId(@PathVariable Integer taxId) {
        return ResponseEntity.ok(mapper.mapToBusinessDto(service.getBusinessByTaxId(taxId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BusinessDto> newBusiness(@RequestBody final BusinessDto businessDto) {
        Business business = mapper.mapToBusiness(businessDto);
        return new ResponseEntity<>(
                mapper.mapToBusinessDto(service.createNewBusiness(business)),
                HttpStatus.CREATED
        );
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<BusinessDto> updateBusiness(@PathVariable Long id, @RequestBody final BusinessDto businessDto) {
        businessDto.setId(id);
        return ResponseEntity.ok(mapper.mapToBusinessDto(service.updateBusinessInfo(businessDto)));
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteBusiness(@PathVariable Long id) {
        service.deleteBusiness(id);
        return ResponseEntity.ok().build();
    }
}
