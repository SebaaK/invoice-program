package kots.invoiceprogram.service;

import kots.invoiceprogram.model.Invoice;
import kots.invoiceprogram.model.Item;
import kots.invoiceprogram.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static kots.invoiceprogram.GenerateData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository repository;

    @Test
    void shouldUpdateItemList() {
        // given
        doNothing().when(repository).removeAllByInvoice(any(Invoice.class));

        // when
        Set<Item> result = itemService.updateItemList(getInvoice(), getSetListItem());

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(SIZE_ITEM_SET_LIST);
        assertThat(result.iterator().next().getInvoice()).isNotNull();
    }
}