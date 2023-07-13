package ru.practicum.shareit.item.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import ru.practicum.shareit.item.model.Item;

@UtilityClass
public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return new ItemDto(item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable());
    }

    public static Item fromItemDto(ItemDto itemDto) {
        return new Item(itemDto.getId(),
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getAvailable());
    }
}
