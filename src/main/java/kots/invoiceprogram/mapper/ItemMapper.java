package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Item;
import kots.invoiceprogram.model.dto.CreatedItemDto;
import kots.invoiceprogram.model.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ItemMapper {

    public ItemDto mapToItemDto(final Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getQuantity(),
                item.getSingleNetPrice(),
                item.getNetPrice(),
                item.getTaxPercent(),
                item.getTaxValue(),
                item.getGrossPrice(),
                item.getDiscount()
        );
    }

    public Item mapToItem(final CreatedItemDto itemDto) {
        return new Item(
                null,
                itemDto.getName(),
                itemDto.getQuantity(),
                itemDto.getSingleNetPrice(),
                itemDto.getNetPrice(),
                itemDto.getTaxPercent(),
                itemDto.getTaxValue(),
                itemDto.getGrossPrice(),
                itemDto.getDiscount()
        );
    }

    public Set<ItemDto> mapToItemDtoList(final Set<Item> itemList) {
        return itemList.stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toSet());
    }

    public Set<Item> mapToItemDtoList(final List<CreatedItemDto> itemDto) {
        return itemDto.stream()
                .map(this::mapToItem)
                .collect(Collectors.toSet());
    }
}
