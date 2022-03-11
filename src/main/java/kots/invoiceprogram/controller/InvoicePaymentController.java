package kots.invoiceprogram.controller;

import kots.invoiceprogram.mapper.InvoicePaymentMapper;
import kots.invoiceprogram.model.dto.CreatedInvoicePaymentDto;
import kots.invoiceprogram.model.dto.InvoicePaymentDto;
import kots.invoiceprogram.service.InvoicePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business/{idBusiness}/invoices/{idInvoice}/payments")
class InvoicePaymentController {

    private final InvoicePaymentService service;
    private final InvoicePaymentMapper mapper;

    @GetMapping
    ResponseEntity<List<InvoicePaymentDto>> getAllPayment(@PathVariable Long idBusiness, @PathVariable Long idInvoice) {
        return ResponseEntity.ok(mapper.mapToInvoicePaymentDtoList(service.getAllPayments(idBusiness, idInvoice)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<InvoicePaymentDto> addNewPayment(
            @PathVariable Long idBusiness,
            @PathVariable Long idInvoice,
            @RequestBody CreatedInvoicePaymentDto createdInvoicePaymentDto) {
        return new ResponseEntity<>(
                mapper.mapToInvoicePaymentDto(service.addNewPayment(
                        idBusiness, idInvoice, mapper.mapToInvoicePayment(createdInvoicePaymentDto))),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{idPayment}")
    ResponseEntity<Void> deletePayment(@PathVariable Long idBusiness, @PathVariable Long idInvoice, @PathVariable Long idPayment) {
        service.deletePaymentById(idBusiness, idInvoice, idPayment);
        return ResponseEntity.ok().build();
    }
}
