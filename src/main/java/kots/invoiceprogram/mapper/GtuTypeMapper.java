package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.InvoiceGtu;
import kots.invoiceprogram.model.dto.CreatedGtuDto;
import kots.invoiceprogram.model.dto.InvoiceGtuDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GtuTypeMapper {

    public InvoiceGtu mapToInvoiceGtu(final CreatedGtuDto createdGtuDto) {
        return new InvoiceGtu(
                createdGtuDto.getGtuType()
        );
    }

    public InvoiceGtuDto mapToInvoiceGtuDto(final InvoiceGtu invoiceGtu) {
        return new InvoiceGtuDto(invoiceGtu.getId(),invoiceGtu.getGtuType());
    }

    public Set<InvoiceGtuDto> mapToInvoiceGtuDtoSetList(final Set<InvoiceGtu> invoiceList) {
        return invoiceList.stream()
                .map(this::mapToInvoiceGtuDto)
                .collect(Collectors.toSet());
    }

    public Set<InvoiceGtu> mapToInvoiceGtuSetList(final List<CreatedGtuDto> createdGtuDto) {
        return createdGtuDto.stream()
                .map(this::mapToInvoiceGtu)
                .collect(Collectors.toSet());
    }
}
