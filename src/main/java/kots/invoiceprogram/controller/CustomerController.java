package kots.invoiceprogram.controller;

import kots.invoiceprogram.mapper.CustomerMapper;
import kots.invoiceprogram.model.Business;
import kots.invoiceprogram.model.Customer;
import kots.invoiceprogram.model.dto.CustomerDto;
import kots.invoiceprogram.service.BusinessService;
import kots.invoiceprogram.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business/{idBusiness}/customers")
class CustomerController {

    private final BusinessService businessService;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping
    ResponseEntity<List<CustomerDto>> getCustomers(@PathVariable Long idBusiness) {
        return ResponseEntity.ok(customerMapper.mapToCustomerDtoList(customerService.getCustomersList(businessService.getSingleBusiness(idBusiness))));
    }

    @GetMapping("/{idCustomer}")
    ResponseEntity<CustomerDto> getSingleCustomer(@PathVariable Long idBusiness, @PathVariable Long idCustomer) {
        Business business = businessService.getSingleBusiness(idBusiness);
        return ResponseEntity.ok(customerMapper.mapToCustomerDto(customerService.getCustomer(business, idCustomer)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerDto> addNewCustomerToBusiness(@PathVariable Long idBusiness, @RequestBody CustomerDto customerDto) {
        Business business = businessService.getSingleBusiness(idBusiness);
        Customer newCustomer = customerService.createNewCustomer(customerMapper.mapToCustomer(customerDto), business);
        return new ResponseEntity<>(
                customerMapper.mapToCustomerDto(newCustomer),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{idCustomer}")
    ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long idBusiness, @RequestBody CustomerDto customerDto, @PathVariable Long idCustomer) {
        Business business = businessService.getSingleBusiness(idBusiness);
        return ResponseEntity.ok(customerMapper.mapToCustomerDto(customerService.updateCustomer(business, customerDto, idCustomer)));
    }

    @DeleteMapping("/{idCustomer}")
    ResponseEntity<Void> deleteCustomer(@PathVariable Long idBusiness, @PathVariable Long idCustomer) {
        Business business = businessService.getSingleBusiness(idBusiness);
        customerService.deleteCustomer(business, idCustomer);
        return ResponseEntity.ok().build();
    }
}
