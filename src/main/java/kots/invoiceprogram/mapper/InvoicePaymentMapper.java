package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.InvoicePayment;
import kots.invoiceprogram.model.dto.CreatedInvoicePaymentDto;
import kots.invoiceprogram.model.dto.InvoicePaymentDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvoicePaymentMapper {

    public InvoicePaymentDto mapToInvoicePaymentDto(final InvoicePayment invoicePayment) {
        return InvoicePaymentDto.builder()
                .id(invoicePayment.getId())
                .paymentDate(invoicePayment.getPaymentDate())
                .paymentValue(invoicePayment.getPaymentValue())
                .build();
    }

    public InvoicePayment mapToInvoicePayment(final CreatedInvoicePaymentDto createdInvoicePaymentDto) {
        return InvoicePayment.builder()
                .paymentValue(createdInvoicePaymentDto.getPaymentValue())
                .build();
    }

    public List<InvoicePaymentDto> mapToInvoicePaymentDtoList(final List<InvoicePayment> invoicePaymentList) {
        return invoicePaymentList.stream()
                .map(this::mapToInvoicePaymentDto)
                .collect(Collectors.toList());
    }
}
