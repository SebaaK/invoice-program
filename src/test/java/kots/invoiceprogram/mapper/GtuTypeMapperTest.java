package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.InvoiceGtu;
import kots.invoiceprogram.model.dto.CreatedGtuDto;
import kots.invoiceprogram.model.dto.InvoiceGtuDto;
import kots.invoiceprogram.model.selectors.GTUType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GtuTypeMapperTest {

    @Autowired
    private GtuTypeMapper gtuTypeMapper;

    @Test
    void shouldReturnInvoiceGtu() {
        // given
        CreatedGtuDto createdGtuDto = new CreatedGtuDto(GTUType.GTU_12);

        // when
        InvoiceGtu result = gtuTypeMapper.mapToInvoiceGtu(createdGtuDto);

        // then
        assertAll(
                () -> assertEquals(null, result.getId()),
                () -> assertEquals(null, result.getInvoice()),
                () -> assertEquals(GTUType.GTU_12, result.getGtuType())
        );
    }

    @Test
    void shouldReturnInvoiceGtuDto() {
        // given
        InvoiceGtu invoiceGtu = new InvoiceGtu();
        invoiceGtu.setId(1L);
        invoiceGtu.setInvoice(null);
        invoiceGtu.setGtuType(GTUType.GTU_13);

        // when
        InvoiceGtuDto result = gtuTypeMapper.mapToInvoiceGtuDto(invoiceGtu);

        // then
        assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals(GTUType.GTU_13, result.getGtuType())
        );
    }

    @Test
    void shouldReturnSetListInvoiceGtuDto() {
        // given
        Set<InvoiceGtu> invoiceGtuSetList = getInvoiceGtuSetList();

        // when
        Set<InvoiceGtuDto> result = gtuTypeMapper.mapToInvoiceGtuDtoSetList(invoiceGtuSetList);

        // then
        assertThat(result).isNotEmpty();
    }

    @Test
    void shouldReturnSetListInvoiceGtu() {
        // given
        List<CreatedGtuDto> invoiceGtuDtoSetList = new ArrayList<>();
        invoiceGtuDtoSetList.add(new CreatedGtuDto(GTUType.GTU_12));
        invoiceGtuDtoSetList.add(new CreatedGtuDto(GTUType.GTU_13));

        // when
        Set<InvoiceGtu> result = gtuTypeMapper.mapToInvoiceGtuSetList(invoiceGtuDtoSetList);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(2);
    }

    private Set<InvoiceGtu> getInvoiceGtuSetList() {
        Set<InvoiceGtu> invoiceGto = new HashSet<>();
        for(long i = 1; i < 2; i++) {
            InvoiceGtu invoiceGtu = new InvoiceGtu();
            invoiceGtu.setId(i);
            invoiceGtu.setInvoice(null);
            invoiceGtu.setGtuType(GTUType.GTU_13);

            invoiceGto.add(invoiceGtu);
        }
        return invoiceGto;
    }
}