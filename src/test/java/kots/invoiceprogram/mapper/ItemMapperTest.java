package kots.invoiceprogram.mapper;

import kots.invoiceprogram.model.Item;
import kots.invoiceprogram.model.dto.CreatedItemDto;
import kots.invoiceprogram.model.dto.ItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemMapperTest {

    @Autowired
    private ItemMapper itemMapper;

    @Test
    void shouldReturnItemDto() {
        // given
        Item item = new Item(
                1L,
                "Example Item",
                1,
                new BigDecimal(100),
                new BigDecimal(100),
                0.23,
                new BigDecimal(23),
                new BigDecimal(123),
                0.12);

        // when
        ItemDto result = itemMapper.mapToItemDto(item);

        // then
        assertAll(
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals("Example Item", result.getName()),
                () -> assertEquals(1, result.getQuantity()),
                () -> assertEquals(new BigDecimal(100), result.getSingleNetPrice()),
                () -> assertEquals(new BigDecimal(100), result.getNetPrice()),
                () -> assertEquals(0.23, result.getTaxPercent()),
                () -> assertEquals(new BigDecimal(23), result.getTaxValue()),
                () -> assertEquals(new BigDecimal(123), result.getGrossPrice()),
                () -> assertEquals(0.12, result.getDiscount())
        );
    }

    @Test
    void shouldReturnItem() {
        // given
        CreatedItemDto itemDto = new CreatedItemDto();
        itemDto.setName("Example Item");
        itemDto.setQuantity(1);
        itemDto.setSingleNetPrice(new BigDecimal(100));
        itemDto.setNetPrice(new BigDecimal(100));
        itemDto.setTaxPercent(0.23);
        itemDto.setTaxValue(new BigDecimal(23));
        itemDto.setGrossPrice(new BigDecimal(123));
        itemDto.setDiscount(0.12);

        // when
        Item result = itemMapper.mapToItem(itemDto);

        // then
        assertAll(
                () -> assertEquals(null, result.getId()),
                () -> assertEquals("Example Item", result.getName()),
                () -> assertEquals(1, result.getQuantity()),
                () -> assertEquals(new BigDecimal(100), result.getSingleNetPrice()),
                () -> assertEquals(new BigDecimal(100), result.getNetPrice()),
                () -> assertEquals(0.23, result.getTaxPercent()),
                () -> assertEquals(new BigDecimal(23), result.getTaxValue()),
                () -> assertEquals(new BigDecimal(123), result.getGrossPrice()),
                () -> assertEquals(0.12, result.getDiscount())
        );
    }

    @Test
    void shouldReturnSetItemDtoList() {
        // given
        Set<Item> itemList = getSetItemList();

        // when
        Set<ItemDto> result = itemMapper.mapToItemDtoList(itemList);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(9);
    }

    @Test
    void shouldReturnSetItemList() {
        // given
        List<CreatedItemDto> createdItemList = getCreatedItemList();

        // when
        Set<Item> result = itemMapper.mapToItemDtoList(createdItemList);

        // then
        assertThat(result).isNotEmpty();
        assertThat(result).hasSize(10);
    }

    private List<CreatedItemDto> getCreatedItemList() {
        List<CreatedItemDto> itemDtos = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            CreatedItemDto itemDto = new CreatedItemDto();
            itemDto.setName("Example Item #" + i);
            itemDto.setQuantity(i);
            itemDto.setSingleNetPrice(new BigDecimal(100));
            itemDto.setNetPrice(new BigDecimal(100));
            itemDto.setTaxPercent(0.23);
            itemDto.setTaxValue(new BigDecimal(23));
            itemDto.setGrossPrice(new BigDecimal(123));
            itemDto.setDiscount(0.12);
            itemDtos.add(itemDto);
        }
        return itemDtos;
    }

    private Set<Item> getSetItemList() {
        Set<Item> items = new HashSet<>();
        for(long i = 1; i < 10; i++) {
            items.add(
                    new Item(
                            i,
                            "Example Item #" + i,
                            (int) i,
                            new BigDecimal(100),
                            new BigDecimal(100),
                            0.23,
                            new BigDecimal(23),
                            new BigDecimal(123),
                            0.12)
            );
        }
        return items;
    }
}