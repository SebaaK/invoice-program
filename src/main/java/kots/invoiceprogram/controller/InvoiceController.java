package kots.invoiceprogram.controller;

import kots.invoiceprogram.mapper.InvoiceMapper;
import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.dto.CreatedInvoiceDto;
import kots.invoiceprogram.model.dto.InvoiceDto;
import kots.invoiceprogram.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/business/{idBusiness}/invoices")
class InvoiceController {

    private final InvoiceService invoiceService;
    private final InvoiceMapper invoiceMapper;

    @GetMapping
    ResponseEntity<List<InvoiceDto>> getAllInvoices(@PathVariable Long idBusiness) {
        return ResponseEntity.ok(invoiceMapper.invoiceDtoWithoutDetailsList(invoiceService.getAllInvoices(idBusiness)));
    }

    @GetMapping("/{idInvoice}")
    ResponseEntity<InvoiceDto> getSingleInvoice(@PathVariable Long idBusiness, @PathVariable Long idInvoice) {
        return ResponseEntity.ok(invoiceMapper.invoiceDtoWithFullDetails(invoiceService.getSingleInvoice(idInvoice, idBusiness)));
    }

    @PostMapping("/{idCustomer}")
    ResponseEntity<InvoiceDto> createNewInvoiceForCustomer(@PathVariable Long idBusiness, @PathVariable Long idCustomer, @RequestBody CreatedInvoiceDto invoiceDto) {
        Invoice newInvoice = invoiceService.createNewInvoiceForCustomer(idBusiness, idCustomer, invoiceMapper.mapToInvoice(invoiceDto));
        return ResponseEntity.ok(invoiceMapper.invoiceDtoWithoutDetails(newInvoice));
    }
}
