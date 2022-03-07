package kots.invoiceprogram.service;

import kots.invoiceprogram.model.*;
import kots.invoiceprogram.model.dto.CreatedInvoiceDto;
import kots.invoiceprogram.repository.InvoiceGtuRepository;
import kots.invoiceprogram.repository.InvoiceRepository;
import kots.invoiceprogram.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository repository;
    private final BusinessService businessService;
    private final CustomerService customerService;
    private final ItemService itemService;
    private final InvoiceGtuService invoiceGtuService;

    public List<Invoice> getAllInvoices(Long idBusiness) {
        return repository.findAllByBusiness(businessService.getSingleBusiness(idBusiness));
    }

    public Invoice getSingleInvoice(Long idBusiness, Long idInvoice) {
        return repository.searchByIdAndBusinessId(idInvoice, idBusiness).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Invoice createNewInvoiceForCustomer(Long idBusiness, Long idCustomer, Invoice invoice) {
        Business business = businessService.getSingleBusiness(idBusiness);
        Customer customer = customerService.getCustomer(business, idCustomer);

        for(InvoiceGtu gtu : invoice.getGtuType()) {
            gtu.setInvoice(invoice);
        }

        for(Item item : invoice.getItemList()) {
            item.setInvoice(invoice);
        }

        invoice.setBusiness(business);
        invoice.setCustomer(customer);
        return repository.save(invoice);
    }

    @Transactional
    public Invoice updateInvoice(Long idBusiness, Long idInvoice, Invoice requestInvoice) {
        Invoice invoice = getSingleInvoice(idBusiness, idInvoice);
        invoice.setItemList(itemService.updateItemList(invoice, requestInvoice.getItemList()));
        invoice.setGtuType(invoiceGtuService.updateGtuList(invoice, requestInvoice.getGtuType()));
        invoice.setPaymentMethod(requestInvoice.getPaymentMethod());
        invoice.setInvoiceNumber(requestInvoice.getInvoiceNumber());
        invoice.setIssueDate(requestInvoice.getIssueDate());
        invoice.setDueDate(requestInvoice.getDueDate());
        invoice.setGrossPrice(requestInvoice.getGrossPrice());
        invoice.setCurrencyName(requestInvoice.getCurrencyName());
        invoice.setOtherCurrencyName(requestInvoice.getOtherCurrencyName());
        invoice.setOtherCurrencyGrossPrice(requestInvoice.getOtherCurrencyGrossPrice());
        invoice.setExchangeRate(requestInvoice.getExchangeRate());

        return invoice;
    }

    public void deleteInvoice(Long idBusiness, Long idInvoice) {
        repository.delete(getSingleInvoice(idInvoice, idBusiness));
    }
}
