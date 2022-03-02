package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.dto.InvoiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceMapper {

    private final BusinessMapper businessMapper;
    private final CustomerMapper customerMapper;
    private final ItemMapper itemMapper;

    public InvoiceDto invoiceDtoWithoutDetails(final Invoice invoice) {
        return InvoiceDto.builder()
                .id(invoice.getId())
                .paymentMethod(invoice.getPaymentMethod())
                .invoiceNumber(invoice.getInvoiceNumber())
                .issueDate(invoice.getIssueDate())
                .dueDate(invoice.getDueDate())
                .grossPrice(invoice.getGrossPrice())
                .otherCurrencyName(invoice.getOtherCurrencyName())
                .otherCurrencyGrossPrice(invoice.getOtherCurrencyGrossPrice())
                .exchangeRate(invoice.getExchangeRate())
                .build();
    }

    public List<InvoiceDto> invoiceDtoWithoutDetailsList(final List<Invoice> invoiceList) {
        return invoiceList.stream()
                .map(this::invoiceDtoWithoutDetails)
                .collect(Collectors.toList());
    }

    public InvoiceDto invoiceDtoWithFullDetails(final Invoice invoice) {
        return InvoiceDto.builder()
                .id(invoice.getId())
                .business(businessMapper.mapToBusinessDto(invoice.getBusiness()))
                .customer(customerMapper.mapToCustomerDto(invoice.getCustomer()))
                .itemList(itemMapper.mapToItemDtoList(invoice.getItemList()))
                .gtuType(invoice.getGtuType())
                .paymentMethod(invoice.getPaymentMethod())
                .invoiceNumber(invoice.getInvoiceNumber())
                .createdDate(invoice.getCreatedDate())
                .issueDate(invoice.getIssueDate())
                .dueDate(invoice.getDueDate())
                .grossPrice(invoice.getGrossPrice())
                .otherCurrencyName(invoice.getOtherCurrencyName())
                .otherCurrencyGrossPrice(invoice.getOtherCurrencyGrossPrice())
                .exchangeRate(invoice.getExchangeRate())
                .build();
    }
}
