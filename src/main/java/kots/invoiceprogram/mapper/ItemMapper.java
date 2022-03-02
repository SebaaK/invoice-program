package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Item;
import kots.invoiceprogram.model.dto.ItemDto;
import org.springframework.stereotype.Service;

@Service
public class ItemMapper {

    public Item mapToItem(final ItemDto itemDto) {
        Item item = new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getUi(),
                itemDto.getQuantity(),
                itemDto.getNetPrice(),
                itemDto.getTaxPercent(),
                itemDto.getDiscount()
        );
        item.calculateGrossPrice();

        return item;
    }

    public ItemDto mapToItemDto(final Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getUi(),
                item.getQuantity(),
                item.getNetPrice(),
                item.getTaxPercent(),
                item.getTaxValue(),
                item.getGrossPrice(),
                item.getDiscount()
        );
    }
}
