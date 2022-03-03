package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Item;
import kots.invoiceprogram.model.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemMapper {

    public Item mapToItem(final ItemDto itemDto) {
        Item item = new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getQuantity(),
                itemDto.getNetPrice(),
                itemDto.getTaxPercent(),
                itemDto.getTaxValue(),
                itemDto.getGrossPrice(),
                itemDto.getDiscount()
        );

        item.calculateGrossPrice();

        return item;
    }

    public ItemDto mapToItemDto(final Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getQuantity(),
                item.getNetPrice(),
                item.getTaxPercent(),
                item.getTaxValue(),
                item.getGrossPrice(),
                item.getDiscount()
        );
    }

    public Set<ItemDto> mapToItemDtoList(final Set<Item> itemList) {
        return itemList.stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toSet());
    }
}
