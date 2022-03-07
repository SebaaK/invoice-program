package kots.invoiceprogram.controller;

import kots.invoiceprogram.mapper.InvoiceMapper;
import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.dto.CreatedInvoiceDto;
import kots.invoiceprogram.model.dto.InvoiceDto;
import kots.invoiceprogram.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(invoiceMapper.invoiceDtoWithFullDetails(invoiceService.getSingleInvoice(idBusiness, idInvoice)));
    }

    @PostMapping("/customer/{idCustomer}")
    ResponseEntity<InvoiceDto> createNewInvoiceForCustomer(@PathVariable Long idBusiness, @PathVariable Long idCustomer, @RequestBody CreatedInvoiceDto invoiceDto) {
        Invoice newInvoice = invoiceService.createNewInvoiceForCustomer(idBusiness, idCustomer, invoiceMapper.mapToInvoice(invoiceDto));
        return new ResponseEntity<>(
                invoiceMapper.invoiceDtoWithoutDetails(newInvoice),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{idInvoice}")
    ResponseEntity<InvoiceDto> updateInvoice(@PathVariable Long idBusiness, @PathVariable Long idInvoice, @RequestBody CreatedInvoiceDto invoiceDto) {
        Invoice updatedInvoice = invoiceService.updateInvoice(idBusiness, idInvoice, invoiceMapper.mapToInvoice(invoiceDto));
        return ResponseEntity.ok(invoiceMapper.invoiceDtoWithoutDetails(updatedInvoice));
    }

    @DeleteMapping("/{idInvoice}")
    ResponseEntity<Void> deleteInvoice(@PathVariable Long idBusiness, @PathVariable Long idInvoice) {
        invoiceService.deleteInvoice(idBusiness, idInvoice);
        return ResponseEntity.ok().build();
    }
}
